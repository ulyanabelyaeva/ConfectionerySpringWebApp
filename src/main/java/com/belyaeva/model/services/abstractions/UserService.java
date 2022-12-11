package com.belyaeva.model.services.abstractions;

import com.belyaeva.model.entity.UserEntity;

public interface UserService {

    UserEntity getTempUser();

    boolean saveUser(UserEntity user);

}
