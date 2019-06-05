package me.melyukhov.streams.model.repository;

import me.melyukhov.streams.model.dao.Category;
import me.melyukhov.streams.model.dao.Status;
import me.melyukhov.streams.model.dao.Translation;
import me.melyukhov.streams.model.dao.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TranslationRepository extends CrudRepository<Translation, Integer> {

    List<Translation> findByCategory(Category category);
    List<Translation> findByCategoryOrderByCountViewersDesc(Category category);
    List<Translation> findByStatusOrderByCountViewersDesc(Status status);
    Optional<Translation> findByLink(String link);
    Optional<Translation> findByUserInfoAndStatus(UserInfo userInfo, Status status);
    //Optional<Translation> findTopByCountViewers();
}
