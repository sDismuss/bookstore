package com.dismuss.bookstore.service;

import com.dismuss.bookstore.domain.User;
import com.dismuss.bookstore.domain.UserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        UserBuilder builder;
        if (user.isPresent()) {
            User userDetails = user.get();
            builder = org.springframework.security.core.userdetails.User.withUsername(userDetails.getUsername());
            builder.password(userDetails.getPassword());
            builder.roles(userDetails.getRole());
        } else {
            throw new UsernameNotFoundException(username);
        }

        return builder.build();
    }
}
