package me.melyukhov.streams.controller;

import me.melyukhov.streams.service.api.CategoryService;
import me.melyukhov.streams.service.api.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TranslationService translationService;

    @GetMapping(path = "/categories")
    public String mainPage(ModelMap model){
        model.addAttribute("categories", categoryService.getAll());
        return "categories";
    }

    @GetMapping(path = "/translations")
    public String allTranslationsPage(Model model){
        model.addAttribute("translations", translationService.getAllTranslations());
        return "translations";
    }
}
