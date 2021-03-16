package org.demo.clothes.api.manager;

import org.demo.clothes.api.model.ClothesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ClothesManager {

    Page<ClothesEntity> findAll(Pageable pageable);
    List<ClothesEntity> findAll(Specification<ClothesEntity> specification);
    Page<ClothesEntity> findAll(Specification<ClothesEntity> specification,Pageable page);
    Optional<ClothesEntity> findById(String clothesId);
    void saveClothes(ClothesEntity clothesEntity);
    void updateClothes(ClothesEntity clothesEntity);
    void deleteClothes (ClothesEntity clothesEntity);
}
