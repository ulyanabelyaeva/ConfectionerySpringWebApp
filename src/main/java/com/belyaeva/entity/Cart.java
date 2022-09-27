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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    private boolean status;

    @Column(name = "cost")
    private int cost;

    @Column(name = "date")
    private String date;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItem> items;
    public Cart(User user, boolean status) {
        this.user = user;
        this.status = status;
        this.cost = 0;
    }
}
