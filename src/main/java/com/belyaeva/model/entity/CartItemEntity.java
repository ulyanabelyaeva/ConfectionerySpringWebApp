package com.belyaeva.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cartItem")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

}
