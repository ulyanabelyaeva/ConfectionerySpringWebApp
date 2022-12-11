package com.belyaeva.model.repository;

import com.belyaeva.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findAllByUserId(Long id);
}
