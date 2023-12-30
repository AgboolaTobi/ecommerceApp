package com.semicolon.ecommerce.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String storeName;
    private String sellerEmailAddress;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Product> listOfProducts;
}
