package com.example.inventorymanagement.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "InventoryChecks")
public class InventoryCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer actualQuantity;
}
