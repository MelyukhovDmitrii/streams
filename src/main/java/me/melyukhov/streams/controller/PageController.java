package me.melyukhov.streams.controller;

import me.melyukhov.streams.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model){
        model.addAttribute("categories", categoryService.getAll());
        return "home";
    }

}
