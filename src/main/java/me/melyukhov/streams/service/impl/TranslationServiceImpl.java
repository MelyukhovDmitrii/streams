package me.melyukhov.streams.service.impl;

import me.melyukhov.streams.model.dao.*;
import me.melyukhov.streams.model.repository.TranslationKeysRepository;
import me.melyukhov.streams.model.repository.TranslationRepository;
import me.melyukhov.streams.service.api.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

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
        List<Translation> translations = translationRepository.findByCategoryOrderByCountViewersDesc(category);
        translations.forEach(i -> i.setTranslationKeys(null));
        return translations;
    }

    @Override
    public Translation save(Translation translation) {
        return translationRepository.save(translation);
    }

    public void initKeys(Translation translation){
        KeyPairGenerator keyPairGenerator1 = null;
        try {
            keyPairGenerator1 = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(keyPairGenerator1 != null){
            //KeyPair keyPair = keyPairGenerator1 != null ? keyPairGenerator1.generateKeyPair() : null;
            String privatekey = "privateKey";//new String(keyPair.getPrivate().getEncoded());
            String publicKey = "publicKey";//new String(keyPair.getPublic().getEncoded());
            TranslationKeys translationKeys = new TranslationKeys();
            translationKeys.setPrivateKey(privatekey);
            translationKeys.setPublicKey(publicKey);
            translationKeys = translationKeysRepository.save(translationKeys);
            translation.setTranslationKeys(translationKeys);
        }
    }

    @Override
    public List<Translation> getAllTranslations() {
        List<Translation> translationList = translationRepository.findByStatusOrderByCountViewersDesc(Status.ACTIVE);
        return translationList;
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

    @Override
    public Translation getTopTranslation() {
        //return translationRepository.findTopByCountViewers().orElse(null);
        return translationRepository.findByStatusOrderByCountViewersDesc(Status.ACTIVE).get(0);
    }

    @Override
    public List<Translation> getTop3WithoutTop1() {
        List<Translation> translations = translationRepository.findByStatusOrderByCountViewersDesc(Status.ACTIVE);
        translations.remove(0);
        return translations.subList(0, 3);
    }
}
