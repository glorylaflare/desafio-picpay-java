package com.picpay.picpay_simplificado.services;

import com.picpay.picpay_simplificado.domain.user.User;
import com.picpay.picpay_simplificado.domain.user.UserType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionValidator {

    public void validate(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo logista não está autorizado a realizar transação.");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente.");
        }
    }
}
