package com.euler.projetct_marktplace.Service;

import com.euler.projetct_marktplace.Interface.UserInterface;
import com.euler.projetct_marktplace.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements UserInterface {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean register(String nome, String email, String password) {
        return userRepository.register(nome, email, password);
    }

    @Override
    public Map<String, String> login(String email, String password) {
        return userRepository.login(email, password);
    }

}
