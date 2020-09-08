package com.codegym.service.user;

import com.codegym.model.User;
import com.codegym.model.UserDetailCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CustomUserDetailsService  implements UserDetailsService  {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> users = userService.findByEmail(email);

        if (!users.isPresent()) {
            throw new UsernameNotFoundException("User not found.");
        }

        return new UserDetailCustom(users.get());
    }
}
