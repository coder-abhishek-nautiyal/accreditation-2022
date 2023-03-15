package com.accreditation.userservice.service;

import com.accreditation.userservice.entity.UserDetail;
import com.accreditation.userservice.repository.UserRepository;
import com.accreditation.userservice.util.ExceptionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetail save(UserDetail userDetail) {

        Optional<UserDetail> userDetailsByEmail = userRepository.findUserByEmail(userDetail.getEmail());

        if (userDetailsByEmail.isPresent()) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstant.EMAIL_ID_ALREADY_EXIST);
        }

        Optional<UserDetail> userDetailsByUsername = userRepository.findUserByUsername(userDetail.getUsername());

        if (userDetailsByUsername.isPresent()) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstant.USER_NAME_ALREADY_EXIST);
        }

        return userRepository.save(userDetail);


    }

    @Override
    public UserDetail findUserByEmail(String emailId) {

        Optional<UserDetail> userDetailsByEmail = userRepository.findUserByEmail(emailId);

        if (!userDetailsByEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstant.EMAIL_ID_NOT_EXIST);
        }

        return userDetailsByEmail.get();

    }
}
