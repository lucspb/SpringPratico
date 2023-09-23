package com.github.lucspb.pdv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(length = 20, precision = 20, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(length = 100, nullable = false)
    private int quantity;
}
