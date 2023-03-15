package com.accreditation.userservice.service;

import com.accreditation.userservice.entity.UserDetail;
import com.accreditation.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;


        Optional<UserDetail> userDetails = userRepository.findUserByUsername(username);
        if (userDetails.isPresent()) {
            roles = Arrays.asList(new SimpleGrantedAuthority(userDetails.get().getRole()));
            return new User(userDetails.get().getUsername(), userDetails.get().getPassword(), roles);
        }
        throw new UsernameNotFoundException("User not found with the name " + username);
    }


}
