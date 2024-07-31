package com.picpay.picpay_simplificado.services;

import com.picpay.picpay_simplificado.domain.user.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class TransactionAuthorizer {

    private WebClient webclient;

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        webclient = WebClient.builder().baseUrl("https://util.devi.tools/api/v2").build();

        var response = webclient.get()
                .uri("/authorize")
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return "success".equalsIgnoreCase((String) response.get("status"));
    }
}
