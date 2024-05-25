package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.model.IssueRecord;
import com.example.inventorymanagement.repository.IssueRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issuerecords")
public class IssueRecordController {

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @PostMapping
    public IssueRecord createIssueRecord(@RequestBody IssueRecord issueRecord) {
        return issueRecordRepository.save(issueRecord);
    }
}
