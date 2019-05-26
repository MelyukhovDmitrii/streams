package me.melyukhov.streams.model.repository;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.dao.Translation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TranslationRepository extends CrudRepository<Translation, Integer> {

    List<Translation> findByCategory(Category category);
}
