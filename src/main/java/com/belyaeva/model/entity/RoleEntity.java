package com.belyaeva.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
public class RoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;

    public RoleEntity(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
