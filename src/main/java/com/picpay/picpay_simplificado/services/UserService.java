package com.picpay.picpay_simplificado.services;

import com.picpay.picpay_simplificado.domain.user.User;
import com.picpay.picpay_simplificado.domain.user.UserType;
import com.picpay.picpay_simplificado.dtos.UserDTO;
import com.picpay.picpay_simplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validadeTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHAN) {
            throw new Exception("Usuário do tipo logista não está autorizado a realizar transação.");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente.");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);

        return  newUser;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
