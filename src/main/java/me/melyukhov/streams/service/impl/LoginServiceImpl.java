package me.melyukhov.streams.service.impl;

import me.melyukhov.streams.model.dao.UserInfo;
import me.melyukhov.streams.model.repository.UserInfoRepository;
import me.melyukhov.streams.service.api.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {

        Optional<UserInfo> userInfoOptional = userInfoRepository.findByLogin(string);

        if(!userInfoOptional.isPresent()){
            throw  new UsernameNotFoundException("user not found");
        }

        return new MyLogin(userInfoOptional.get());
    }
}
