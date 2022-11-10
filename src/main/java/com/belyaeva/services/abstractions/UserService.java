package com.belyaeva.services.abstractions;

import com.belyaeva.entity.User;

public interface UserService {

    User getTempUser();

    boolean saveUser(User user);

}
