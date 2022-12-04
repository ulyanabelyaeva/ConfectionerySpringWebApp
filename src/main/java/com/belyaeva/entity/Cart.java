package com.belyaeva.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {

    public static Cart fromUser(User user) {
        return new Cart(user, false, false, 0);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    private boolean status;

    @Column(name = "ready")
    private boolean ready;

    @Column(name = "cost")
    private int cost;

    @Column(name = "date")
    private String date;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItem> items;
}
