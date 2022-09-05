package com.belyaeva.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String email;

    private String firstName;

    private String password;

    private List<Product> cart;

    private List<List<Product>> orders;
}
