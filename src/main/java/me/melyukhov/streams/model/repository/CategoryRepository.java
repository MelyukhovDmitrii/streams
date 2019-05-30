package me.melyukhov.streams.model.repository;

import me.melyukhov.streams.model.dao.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Optional<Category> findByLink(String link);

}
