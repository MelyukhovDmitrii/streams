package me.melyukhov.streams.service.impl;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.dao.Translation;
import me.melyukhov.streams.model.dao.TranslationKeys;
import me.melyukhov.streams.model.dao.UserInfo;
import me.melyukhov.streams.model.repository.TranslationKeysRepository;
import me.melyukhov.streams.model.repository.TranslationRepository;
import me.melyukhov.streams.service.api.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    TranslationRepository translationRepository;

    @Autowired
    TranslationKeysRepository translationKeysRepository;

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
        KeyPairGenerator keyPairGenerator1 = null;
        try {
            keyPairGenerator1 = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(keyPairGenerator1 != null){
            KeyPair keyPair = keyPairGenerator1 != null ? keyPairGenerator1.generateKeyPair() : null;
            String privatekey = new String(keyPair.getPrivate().getEncoded());
            String publicKey = new String(keyPair.getPublic().getEncoded());
            TranslationKeys translationKeys = new TranslationKeys();
            translationKeys.setPrivateKey(privatekey);
            translationKeys.setPublicKey(publicKey);
            translationKeys = translationKeysRepository.save(translationKeys);
            translation.setTranslationKeys(translationKeys);
            return translationRepository.save(translation);
        }
        return null;
    }

    @Override
    public List<Translation> getAllTranslations() {
        List<Translation> translations = new ArrayList<>();
        for(Translation translation: translationRepository.findAll()){
            translations.add(translation);
        }
        return translations.stream().sorted(Comparator.comparing(Translation::getCountViewers).reversed()).collect(Collectors.toList());
    }

    @Override
    public Translation getTranslationByLink(String link) {

        Optional<Translation> translationOptional = translationRepository.findByLink(link);
        Translation translation = null;

        if(translationOptional.isPresent()){
            translation = translationOptional.get();
            UserInfo userInfo = translation.getUserInfo();
            if(userInfo != null){
                userInfo.setEmail(null);
                userInfo.setPassword(null);
                userInfo.setStatus(null);
            } else {
                userInfo = new UserInfo();
            }
        }
        return translation;
    }
}
