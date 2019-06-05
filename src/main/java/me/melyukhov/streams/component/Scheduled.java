package me.melyukhov.streams.component;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.dao.Translation;
import me.melyukhov.streams.model.repository.CategoryRepository;
import me.melyukhov.streams.model.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Scheduled {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TranslationRepository translationRepository;

    @org.springframework.scheduling.annotation.Scheduled(fixedRate = 20000)
    public void updateCategories(){
        for(Category category: categoryRepository.findAll()){
            int count = 0;
            for(Translation translation: translationRepository.findByCategory(category)){
                count += translation.getCountViewers();
            }
            category.setCountViewers(count);
            categoryRepository.save(category);
        }
        System.out.println("Update");
    }
}
