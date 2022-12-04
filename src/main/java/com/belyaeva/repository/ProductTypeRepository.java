package com.belyaeva.repository;

import com.belyaeva.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {

    @Query("SELECT p FROM ProductTypeEntity p WHERE p.name =:param")
    List<ProductTypeEntity> findProductTypeByName(@Param("param") String name);
}
