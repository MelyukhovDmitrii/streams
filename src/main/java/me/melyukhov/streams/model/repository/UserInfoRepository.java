package me.melyukhov.streams.model.repository;

import me.melyukhov.streams.model.dao.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

    Optional<UserInfo> findByLogin(String login);

}
