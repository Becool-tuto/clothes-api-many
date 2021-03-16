package org.demo.clothes.api.repository;

import org.demo.clothes.api.model.ClothesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClothesRepository extends JpaRepository<ClothesEntity,String> , JpaSpecificationExecutor<ClothesEntity> {

    Optional<ClothesEntity> findById(String id);
}
