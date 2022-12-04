package com.belyaeva.services.abstractions;

import com.belyaeva.entity.UserEntity;

public interface UserService {

    UserEntity getTempUser();

    boolean saveUser(UserEntity user);

}
