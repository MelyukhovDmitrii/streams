package me.melyukhov.streams.service.impl;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.dao.Translation;
import me.melyukhov.streams.model.repository.TranslationRepository;
import me.melyukhov.streams.service.api.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    TranslationRepository translationRepository;

    @Override
    public Translation getTranslationById(int id) {
        Optional<Translation> translation = translationRepository.findById(id);
        translation.orElse(null).setTranslationKeys(null);
        translation.orElse(null).setCategory(null);
        return translation.orElse(null);
    }

    @Override
    public List<Translation> getTranslationByCategory(Category category) {
        List<Optional<Translation>> translations = translationRepository.findByCategory(category);
        translations.sort((a, b)->a.get().getCountViewers() > b.get().getCountViewers() ? a.get().getCountViewers() : b.get().getCountViewers());
        translations.forEach(i -> i.get().setTranslationKeys(null));
        return translations.stream().map(i -> i.get()).collect(Collectors.toList());
    }

    @Override
    public Translation save(Translation translation) {
        return translationRepository.save(translation);
    }
}
