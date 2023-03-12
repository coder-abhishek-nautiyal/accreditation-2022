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

        Optional<UserDetail> userDetails = userRepository.findByUsername(userDetail.getUsername());

        if (userDetails.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstant.USER_NAME_ALREADY_PRESENT);
        } else {
            return userRepository.save(userDetail);
        }

    }
}
