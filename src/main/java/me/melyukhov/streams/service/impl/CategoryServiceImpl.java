package me.melyukhov.streams.service.impl;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.repository.CategoryRepository;
import me.melyukhov.streams.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public Optional<Category> getById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Iterable<Category> getAll(){
        return categoryRepository.findAll();
    }
}
