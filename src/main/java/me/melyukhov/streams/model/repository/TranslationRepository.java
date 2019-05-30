package me.melyukhov.streams.model.repository;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.dao.Translation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TranslationRepository extends CrudRepository<Translation, Integer> {

    List<Optional<Translation>> findByCategory(Category category);
    Optional<Translation> findByLink(String link);
}
