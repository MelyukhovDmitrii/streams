package me.melyukhov.streams.service.api;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.dao.Translation;

import java.util.List;

public interface TranslationService {

    Translation getTranslationById(int id);

    List<Translation> getTranslationByCategory(Category category);

}
