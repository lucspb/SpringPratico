package com.github.lucspb.pdv.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(length = 100, nullable = false)
    private String name;

    private boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;


}
