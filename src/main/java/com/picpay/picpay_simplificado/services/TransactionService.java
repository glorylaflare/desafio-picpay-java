package com.picpay.picpay_simplificado.services;

import com.picpay.picpay_simplificado.domain.transaction.Transaction;
import com.picpay.picpay_simplificado.domain.user.User;
import com.picpay.picpay_simplificado.dtos.TransactionDTO;
import com.picpay.picpay_simplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validadeTransaction(sender, transaction.value());

        boolean isAutorized = this.authorizeTransaction(sender, transaction.value());

        if(!isAutorized) {
            throw new Exception("Transação não autorizada.");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            String status = response.getBody().get("status").toString();
            return "sucess".equalsIgnoreCase(status);
        } else {
            return false;
        }
    }
}
