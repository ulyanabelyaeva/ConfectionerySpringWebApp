package com.belyaeva.repository;

import com.belyaeva.entity.CartItem;
import com.belyaeva.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    @Query("SELECT p FROM ProductType p WHERE name =:param")
    List<ProductType> findProductTypeByName(@Param("param") String name);
}
