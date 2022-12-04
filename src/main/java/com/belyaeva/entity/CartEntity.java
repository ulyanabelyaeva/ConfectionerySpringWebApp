package com.belyaeva.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "status")
    private boolean status;

    @Column(name = "ready")
    private boolean ready;

    @Column(name = "cost")
    private int cost;

    @Column(name = "date")
    private String date;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItemEntity> items;
}
