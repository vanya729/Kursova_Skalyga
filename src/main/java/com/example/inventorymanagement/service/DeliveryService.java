package com.example.inventorymanagement.service;


import com.example.inventorymanagement.exception.ResourceNotFoundException;
import com.example.inventorymanagement.model.Delivery;
import com.example.inventorymanagement.model.Product;
import com.example.inventorymanagement.repository.DeliveryRepository;
import com.example.inventorymanagement.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);

    public void addDeliveryFromProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        logger.info("Product found: {}", product);

        Delivery delivery = new Delivery();
        delivery.setDeliveryDate(product.getDeliveryDate());
        delivery.setSupplier(product.getSupplier());
        delivery.setProduct(product);
        delivery.setQuantity(product.getQuantity());
        delivery.setCost(product.getCost());

        deliveryRepository.save(delivery);

        logger.info("Delivery created: {}", delivery);
    }
}
