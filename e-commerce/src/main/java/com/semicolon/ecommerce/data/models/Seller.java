package com.semicolon.ecommerce.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sellerName;
    private String emailAddress;
    private String address;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Store store;
}
