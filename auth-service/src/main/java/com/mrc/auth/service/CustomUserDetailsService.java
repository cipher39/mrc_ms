package com.mrc.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mrc.auth.entity.User;
import com.mrc.auth.repository.UserRepository;
import com.mrc.auth.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
//                .orElseThrow(() ->
//                        new UsernameNotFoundException(
//                                "User not found : " + username));

        return new CustomUserDetails(user);
    }
}