package com.picpay.picpay_simplificado.services;

import com.picpay.picpay_simplificado.domain.user.User;
import com.picpay.picpay_simplificado.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        System.out.println("Notificação enviada para o usuário.");
    }
}
