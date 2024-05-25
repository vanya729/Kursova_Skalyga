package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.service.IssueRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issueRecords")
public class IssueRecordController {
    @Autowired
    private IssueRecordService issueRecordService;

    @PostMapping("/issue")
    public ResponseEntity<String> issueProduct(@RequestParam Long productId,
                                               @RequestParam int quantity,
                                               @RequestParam String signedBy) {
        try {
            issueRecordService.issueProduct(productId, quantity, signedBy);
            return ResponseEntity.ok("Product issued successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
