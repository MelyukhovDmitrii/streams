package me.melyukhov.streams.controller;

import me.melyukhov.streams.service.api.CategoryService;
import me.melyukhov.streams.service.api.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TranslationService translationService;

    @GetMapping(path = "/categories")
    public String mainPage(ModelMap model){
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("active_categories", "active");
        model.addAttribute("page", "categories");
        return "main";
    }

    @GetMapping(path = "/category/{id}")
    public String concreteCategory(Model model, @PathVariable String id){
        model.addAttribute("translations", categoryService.getByLink(id).getTranslations());
        model.addAttribute("active_translations", "active");
        model.addAttribute("page", "translations");
        return "main";
    }

    @GetMapping(path = "/translations")
    public String allTranslationsPage(Model model){
        model.addAttribute("translations", translationService.getAllTranslations());
        model.addAttribute("active_translations", "active");
        model.addAttribute("page", "translations");
        return "main";
    }

    @GetMapping(path = "/translation/{id}")
    public String getTranslation(Model model, @PathVariable String id){
        model.addAttribute("translation", translationService.getTranslationByLink(id));
        model.addAttribute("active_translation", "active");
        model.addAttribute("page", "translation");
        return "main";
    }

    @GetMapping(path = "/")
    public String home(Model model){
        model.addAttribute("translation", translationService.getTopTranslation());
        model.addAttribute("active_main","active");
        model.addAttribute("page", "home");
        model.addAttribute("categories", categoryService.getAll().subList(0, 5));
        model.addAttribute("translations", translationService.getAllTranslations().subList(0, 3));
        return "main";
    }

    @GetMapping(path = "/login")
    public String login(){
        return "login";
    }
}
