package me.melyukhov.streams.service.impl;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.dao.Translation;
import me.melyukhov.streams.model.repository.TranslationRepository;
import me.melyukhov.streams.service.api.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    TranslationRepository translationRepository;

    @Override
    public Translation getTranslationById(int id) {
        Optional<Translation> translation = translationRepository.findById(id);
        return translation.get();
    }

    @Override
    public List<Translation> getTranslationByCategory(Category category) {
        List<Translation> translations = translationRepository.findByCategory(category);
        translations.sort((a, b)->a.getCountViewers() > b.getCountViewers() ? a.getCountViewers() : b.getCountViewers());
        return translations;
    }
}
