package com.bumblebee.users.service;

import com.bumblebee.users.entity.Client;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Client getUser(String login) {
        Client client = new Client();
        client.setLogin(login);
        client.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
        return client;
    }
}
