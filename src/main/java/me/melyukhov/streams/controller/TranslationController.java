package me.melyukhov.streams.controller;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.dao.Translation;
import me.melyukhov.streams.model.dao.TranslationKeys;
import me.melyukhov.streams.service.api.CategoryService;
import me.melyukhov.streams.service.api.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/translation")
public class TranslationController {

    /*@Autowired
    TranslationService translationService;

    @Autowired
    CategoryService categoryService;

    /*@GetMapping("/category/{id}")
    public Iterable<Translation> getTranslationsByCategory(@PathVariable Integer id){
        Optional<Category> category = categoryService.getById(id);
        return translationService.getTranslationByCategory(category.get());
    }

    @GetMapping("/{id}")
    public Translation getTranslatinById(@PathVariable Integer id){
        Translation translation = new Translation();
        TranslationKeys translationKeys = new TranslationKeys();
        translationKeys.setPublicKey("sassf");
        translationKeys.setPrivateKey("qfwff");
        translation.setName("name");
        translation.setTranslationKeys(translationKeys);
        Category category = new Category();
        category.setName("category");
        translation.setCategory(category);
        translation = translationService.save(translation);
        Translation translation1 = translationService.getTranslationById(translation.getId());
        return translation;
    }

*/
}
