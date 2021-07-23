package com.bumblebee.users.service;

import com.bumblebee.users.entity.Role;
import com.bumblebee.users.entity.User;
import com.bumblebee.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsManager {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = findUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + login);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        user.setRole(Role.USER);
        user.setLogin(userDetails.getUsername());
        user.setPassword(encoder.encode(userDetails.getPassword()));
        repository.save(user);
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        User user = findUserByLogin(userDetails.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + userDetails.getUsername());
        }
        createUser(userDetails);
    }

    @Override
    public void deleteUser(String username) {
        User user = findUserByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }
        repository.delete(user);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        //todo реализовать через контекст
    }

    @Override
    public boolean userExists(String login) {
        return Optional.of(findUserByLogin(login)).isPresent();
    }

    private User findUserByLogin(String login) {
        return repository.findByLogin(login);
    }
}
