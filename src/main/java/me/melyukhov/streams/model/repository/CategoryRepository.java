package me.melyukhov.streams.model.repository;

import me.melyukhov.streams.model.dao.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
