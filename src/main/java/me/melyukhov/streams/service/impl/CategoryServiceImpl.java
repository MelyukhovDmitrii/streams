package me.melyukhov.streams.service.impl;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.repository.CategoryRepository;
import me.melyukhov.streams.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public Category getById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAll(){
        List<Category> categories = new ArrayList<>();
        for(Category category: categoryRepository.findAll()){
            categories.add(category);
        }
        return categories.stream().sorted(Comparator.comparing(Category::getCountViewers).reversed()).collect(Collectors.toList());
    }

    @Override
    public Category getByLink(String link) {
        return categoryRepository.findByLink(link).orElse(null);
    }

    @Override
    public Category getByCategory(String category) {
        return categoryRepository.findByName(category).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
