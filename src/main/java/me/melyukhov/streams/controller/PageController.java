package me.melyukhov.streams.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import me.melyukhov.streams.model.dao.Translation;
import me.melyukhov.streams.service.api.CategoryService;
import me.melyukhov.streams.service.api.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.engine.TemplateManager;
import org.thymeleaf.engine.TemplateModel;
import org.thymeleaf.spring5.view.ThymeleafView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.standard.expression.Fragment;

import java.util.List;

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
}
