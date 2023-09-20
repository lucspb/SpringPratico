package com.github.lucspb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100)
    private String description;

    @NotBlank
    @NotNull
    @Column(length = 20, precision = 20, scale = 2)
    private BigDecimal price;

    @NotBlank
    @NotNull
    private int quantity;
}
