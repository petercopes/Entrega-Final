package com.coderhouse.service.impl;

import com.coderhouse.model.database.document.OrderDocument;
import com.coderhouse.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    @Override
    public void sendEmail(OrderDocument order) {
        var message = new SimpleMailMessage();
        message.setTo("petercopes.96@gmail.com");

        message.setSubject("Orden de compra"+order.getId());
        message.setText("Nueva orden de compra para "+order.getEmail()+ "\n" +
                "Fecha y hora de creacion de la orden" + order.getDate() + "\n" +
                "Numero de orden:" + order.getId() + "\n" +
                "Listado de productos" + order.getItems().toString()
        );

        javaMailSender.send(message);
    }
}