package com.accreditation.userservice.service;

import com.accreditation.userservice.entity.UserDetail;

public interface UserService {
    UserDetail save(UserDetail userDetail);

    UserDetail findUserByEmail(String emailId);
}
