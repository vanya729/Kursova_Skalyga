package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE (:id IS NULL OR p.id = :id) " +
            "AND (:name IS NULL OR p.name LIKE %:name%) " +
            "AND (:supplier IS NULL OR p.supplier LIKE %:supplier%) " +
            "AND (:deliveryDate IS NULL OR p.deliveryDate = :deliveryDate)")
    List<Product> findByCriteria(@Param("id") Long id,
                                 @Param("name") String name,
                                 @Param("supplier") String supplier,
                                 @Param("deliveryDate") LocalDate deliveryDate);
}
