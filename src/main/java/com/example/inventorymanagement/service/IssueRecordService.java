package com.example.inventorymanagement.service;

import com.example.inventorymanagement.exception.ResourceNotFoundException;
import com.example.inventorymanagement.model.IssueRecord;
import com.example.inventorymanagement.model.Product;
import com.example.inventorymanagement.repository.IssueRecordRepository;
import com.example.inventorymanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IssueRecordService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    public void issueProduct(Long productId, int quantity, String signedBy, String receivedBy) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough product in stock");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setProduct(product);
        issueRecord.setProductName(product.getName());
        issueRecord.setQuantity(quantity);
        issueRecord.setSignedBy(signedBy);
        issueRecord.setReceivedBy(receivedBy);
        issueRecord.setIssueDate(new Date());

        issueRecordRepository.save(issueRecord);
    }
}
