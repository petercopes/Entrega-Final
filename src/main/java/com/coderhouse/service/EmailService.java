package com.coderhouse.service;

import com.coderhouse.model.database.document.OrderDocument;
import org.springframework.core.annotation.Order;

public interface EmailService {
    public void sendEmail(OrderDocument order);
}
