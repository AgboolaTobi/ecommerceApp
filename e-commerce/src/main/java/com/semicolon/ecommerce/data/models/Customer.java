package com.semicolon.ecommerce.data.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String email;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Cart cart;
}
