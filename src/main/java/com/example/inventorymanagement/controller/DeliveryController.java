package com.example.inventorymanagement.controller;


import com.example.inventorymanagement.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    @PostMapping("/fromProduct/{productId}")
    public ResponseEntity<String> createDeliveryFromProduct(@PathVariable Long productId) {
        logger.info("Received request to create delivery from product with ID: {}", productId);
        try {
            deliveryService.addDeliveryFromProduct(productId);
            return ResponseEntity.ok("Delivery created successfully.");
        } catch (Exception e) {
            logger.error("Error creating delivery: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating delivery.");
        }
    }
}
