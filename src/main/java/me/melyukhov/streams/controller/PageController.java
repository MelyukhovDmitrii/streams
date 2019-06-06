package me.melyukhov.streams.controller;

import me.melyukhov.streams.model.dao.*;
import me.melyukhov.streams.model.dto.RegisterDTO;
import me.melyukhov.streams.model.dto.TranslationDTO;
import me.melyukhov.streams.model.repository.TranslationRepository;
import me.melyukhov.streams.model.repository.UserInfoRepository;
import me.melyukhov.streams.service.api.CategoryService;
import me.melyukhov.streams.service.api.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PageController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private TranslationService translationService;

    private UserInfoRepository userInfoRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PageController(UserInfoRepository userInfoRepository){
        this.userInfoRepository = userInfoRepository;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping(path = "/translation/{id}/drop")
    public String closeTranslation(Model model, @AuthenticationPrincipal Principal principal, @PathVariable String id){
        if(principal == null){
            return getTranslation(model, id, principal);
        }
        Optional<Translation> translationOptional = findTranslationByPrinipal(principal);
        if (translationOptional.isPresent()){
            Translation translation = translationOptional.get();
            if (translation.getLink().equals(id)) {
                translation.setStatus(Status.BLOCK);
                translationRepository.save(translation);
            }
        }
        return home(model, principal);
    }

    @GetMapping(path = "/categories")
    public String mainPage(Model model, @AuthenticationPrincipal Principal principal){
        defaultUserService(model, principal);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("active_categories", "active");
        model.addAttribute("page", "categories");
        List<String> formCategories = categoryService.getAll().stream().map(i -> i.getName()).collect(Collectors.toList());
        model.addAttribute("form_categories", formCategories);
        return "main";
    }

    @GetMapping(path = "/category/{id}")
    public String concreteCategory(Model model, @PathVariable String id, @AuthenticationPrincipal Principal principal){
        defaultUserService(model, principal);
        List<Translation> translationList = translationRepository.findByCategoryOrderByCountViewersDesc(categoryService.getByLink(id));
        model.addAttribute("translations", translationList);
        model.addAttribute("active_translations", "active");
        model.addAttribute("page", "translations");
        List<String> formCategories = categoryService.getAll().stream().map(i -> i.getName()).collect(Collectors.toList());
        model.addAttribute("form_categories", formCategories);
        return "main";
    }

    @GetMapping(path = "/translations")
    public String allTranslationsPage(Model model, @AuthenticationPrincipal Principal principal){
        defaultUserService(model, principal);
        model.addAttribute("translations", translationService.getAllTranslations());
        model.addAttribute("active_translations", "active");
        model.addAttribute("page", "translations");
        List<String> formCategories = categoryService.getAll().stream().map(i -> i.getName()).collect(Collectors.toList());
        model.addAttribute("form_categories", formCategories);
        return "main";
    }

    @GetMapping(path = "/translation/{id}")
    public String getTranslation(Model model, @PathVariable String id, @AuthenticationPrincipal Principal principal){
        defaultUserService(model, principal);
        isTranslationOfPrincipal(model, principal, id);
        model.addAttribute("translation", translationService.getTranslationByLink(id));
        model.addAttribute("active_translation", "active");
        model.addAttribute("page", "translation");
        List<String> formCategories = categoryService.getAll().stream().map(i -> i.getName()).collect(Collectors.toList());
        model.addAttribute("form_categories", formCategories);
        return "main";
    }

    @GetMapping(path = "/")
    public String home(Model model, @AuthenticationPrincipal Principal principal){
        defaultUserService(model, principal);
        model.addAttribute("translation", translationService.getTopTranslation());
        model.addAttribute("active_main","active");
        model.addAttribute("page", "home");
        model.addAttribute("categories", categoryService.getAll().subList(0, 5));
        model.addAttribute("translations", translationService.getAllTranslations().subList(0, 3));
        List<String> formCategories = categoryService.getAll().stream().map(i -> i.getName()).collect(Collectors.toList());
        model.addAttribute("form_categories", formCategories);
        return "main";
    }

    @PostMapping(path = "/register")
    public String register(RegisterDTO registerDTO){
        if(userInfoRepository.findByLogin(registerDTO.getUsername()).isPresent()){
            return "login?error";
        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(registerDTO.getEmail());
            String password = bCryptPasswordEncoder.encode(registerDTO.getPassword());
            userInfo.setPassword(password);
            userInfo.setLogin(registerDTO.getUsername());
            userInfo.setDateRegistration(new Date());
            userInfoRepository.save(userInfo);
            return "login";
        }
    }

    @PostMapping(path = "/create/translation")
    public String newTranslation(Model model, TranslationDTO translationDTO, @AuthenticationPrincipal Principal principal){
        if(principal == null || userInfoRepository.findByLogin(principal.getName()).get().getStatus() != Status.ACTIVE){
            return home(model, principal);
        } else {
            Translation translation = new Translation();
            translation.setName(translationDTO.getTranslationName());
            UserInfo userInfo = userInfoRepository.findByLogin(principal.getName()).get();
            translation.setUserInfo(userInfo);
            Category category = categoryService.getByCategory(translationDTO.getCategory());
            if(category == null){
                return home(model, principal);
            }
            translation.setCategory(category);
            translation.setDescription(translationDTO.getDescription());
            String link = userInfo.getLogin().hashCode() + translationDTO.getTranslationName();
            translation.setLink(link);
            translation.setStatus(Status.ACTIVE);
            translation.setPicturePath("'/translations/shum.jpg'");
            TranslationKeys translationKeys= new TranslationKeys();
            translationKeys.setPrivateKey("MBj/HhYNzLiPthOMmLduuI.pxZnJhVpUA/c5v");
            translationKeys.setPublicKey("iPthOMmLBj/HxuI.pxZnJhVpc5duuI.pxZnJhVpUMv");
            translation.setTranslationKeys(translationKeys);
            translationService.save(translation);
            return getTranslation(model, link, principal);
        }
    }

    private void defaultUserService(Model model, Principal principal){
        Optional<Translation> translationOptional = findTranslationByPrinipal(principal);
        if (translationOptional.isPresent()){
            model.addAttribute("haveTranslation", true);
            model.addAttribute("myTranslationLink", "/translation/" + translationOptional.get().getLink());
            model.addAttribute("key", translationOptional.get().getTranslationKeys().getPrivateKey());
        } else {
            model.addAttribute("haveTranslation", false);
        }
    }

    private Optional<Translation> findTranslationByPrinipal(Principal principal){
        if(principal != null){
            Optional<UserInfo> userInfoOptional = userInfoRepository.findByLogin(principal.getName());
            if(userInfoOptional.isPresent()){
                Optional<Translation> translationOptional = translationRepository.findByUserInfoAndStatus(userInfoOptional.get(), Status.ACTIVE);
                if(translationOptional.isPresent()){
                    return translationOptional;
                }
            }
        }
        return Optional.empty();
    }


    private void isTranslationOfPrincipal(Model model, Principal principal, String link){
        Translation translation = translationService.getTranslationByLink(link);
        if (translation != null){
            if (principal != null){
                if (translation.getUserInfo().getLogin().equals(principal.getName())){
                    model.addAttribute("isHis", true);
                }
            }
        }
    }
}
