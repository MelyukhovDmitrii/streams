package me.melyukhov.streams.service.api;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.dao.Translation;

import java.util.List;

public interface TranslationService {

    Translation getTranslationById(int id);

    List<Translation> getTranslationByCategory(Category category);

    Translation save(Translation translation);

    List<Translation> getAllTranslations();

    Translation getTranslationByLink(String link);

    Translation getTopTranslation();

    List<Translation> getTop3WithoutTop1();
}
