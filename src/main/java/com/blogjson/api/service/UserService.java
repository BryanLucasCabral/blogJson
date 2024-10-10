package com.blogjson.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blogjson.api.model.User;
import com.blogjson.api.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User cadastrarUser(User user) {
        Optional<User> userOpt = userRepository.findByUsername(user.getUsername());

        if (userOpt.isPresent()) {
            return null;
        }

        return userRepository.save(user);
    }

    public Page<User> listarUsers(Pageable paginacao){
        return userRepository.findAll(paginacao);
    }

    public User buscarUserPeloId(Long id){
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return null;

    }

    public User buscarUserPeloUsername(String username){
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return null;
    }

    public User atualizarDadosUser(Long id, User dadosUser){
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            user.setUsername(dadosUser.getUsername());
            user.setRole(dadosUser.getRole());
            
            return userRepository.save(user);
        }
        return null;
    }
    
    public Void deletarUser(Long id){ 
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            userRepository.deleteById(id); 
        }
        return null;
    }
}
