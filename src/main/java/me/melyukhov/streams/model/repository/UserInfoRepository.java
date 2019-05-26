package me.melyukhov.streams.model.repository;

import me.melyukhov.streams.model.dao.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
}
