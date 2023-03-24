package com.accreditation.userservice.service;

import com.accreditation.userservice.entity.UserDetail;
import com.accreditation.userservice.repository.UserRepository;
import com.accreditation.userservice.util.ExceptionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetail save(UserDetail userDetail) {

        log.info("Inside save method of Service Impl with userDetails "+userDetail.toString());


        Optional<UserDetail> userDetailsByEmail = userRepository.findUserByEmail(userDetail.getEmail());

        if (userDetailsByEmail.isPresent()) {
            log.error("Inside save method of Service Impl with Exception "+ExceptionConstant.EMAIL_ID_ALREADY_EXIST);
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstant.EMAIL_ID_ALREADY_EXIST);
        }

        Optional<UserDetail> userDetailsByUsername = userRepository.findUserByUsername(userDetail.getUsername());

        if (userDetailsByUsername.isPresent()) {
            log.error("Inside save method of Service Impl with Exception "+ExceptionConstant.USER_NAME_ALREADY_EXIST);
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstant.USER_NAME_ALREADY_EXIST);
        }

        return userRepository.save(userDetail);


    }

    @Override
    public UserDetail findUserByEmail(String emailId) {

        log.info("Inside findUserByEmail method of Service Impl with email "+emailId);

        Optional<UserDetail> userDetailsByEmail = userRepository.findUserByEmail(emailId);

        if (!userDetailsByEmail.isPresent()) {
            log.error("Inside findUserByEmail method of Service Impl with Exception "+ExceptionConstant.EMAIL_ID_NOT_EXIST);
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstant.EMAIL_ID_NOT_EXIST);
        }

        return userDetailsByEmail.get();

    }
}
