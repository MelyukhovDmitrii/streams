package me.melyukhov.streams.service.api;

import me.melyukhov.streams.model.dao.Category;

import java.util.Optional;

public interface CategoryService {

    Optional<Category> getById(Integer id);

}
