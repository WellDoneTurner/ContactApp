package com.contactApplication.ContactApp.service.impl;


import com.contactApplication.ContactApp.repository.UserRepository;
import com.contactApplication.ContactApp.security.domain.AuthenticatedUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login)
                .map(user -> new AuthenticatedUser(
                         login,
                         user.getPassword(),
                         true,
                         true,
                         true,
                         true,
                         emptyList(),
                         user.getUuid()
                 )).orElseThrow(() -> new UsernameNotFoundException(login));

    }
}
