package me.melyukhov.streams.service.impl;

import me.melyukhov.streams.model.dao.UserInfo;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class MyLogin extends User {

    private static final long serialVersionUID = 1L;

    private UserInfo user;

    public MyLogin(UserInfo user) {
        super(user.getLogin(), user.getPassword(), AuthorityUtils.createAuthorityList("ALL"));
        this.user = user;
    }
}