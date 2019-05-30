package me.melyukhov.streams.service.api;

import me.melyukhov.streams.model.dao.Category;

import java.util.Optional;
import java.util.List;

public interface CategoryService {

    Category getById(Integer id);

    List<Category> getAll();

    Category getByLink(String link);
}
