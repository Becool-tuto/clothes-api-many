package org.demo.clothes.api.manager.impl;

import org.demo.clothes.api.manager.ClothesManager;
import org.demo.clothes.api.model.ClothesEntity;
import org.demo.clothes.api.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothesManagerImpl implements ClothesManager {

    @Autowired
    private ClothesRepository clothesRepository;

    @Override
    public Page<ClothesEntity> findAll(Pageable pageable) {
        return  clothesRepository.findAll(pageable);
    }

    @Override
    public List<ClothesEntity> findAll(Specification<ClothesEntity> specification) {
        return clothesRepository.findAll(specification);
    }

    @Override
    public Page<ClothesEntity> findAll(Specification<ClothesEntity> specification, Pageable page) {
        return clothesRepository.findAll(specification,page);
    }

    @Override
    public Optional<ClothesEntity> findById(String clothesId) {
        return clothesRepository.findById(clothesId);
    }

    @Override
    public void saveClothes(ClothesEntity clothesEntity) {
        clothesRepository.save(clothesEntity);
    }

    @Override
    public void updateClothes(ClothesEntity clothesEntity) {
        Optional<ClothesEntity> clothes = clothesRepository.findById(clothesEntity.getId());
        if(clothes.isPresent()){
            clothes.get().setSize(clothesEntity.getSize());
            clothes.get().setColor(clothesEntity.getColor());
            clothesRepository.save(clothes.get());
        }
    }

    @Override
    public void deleteClothes(ClothesEntity clothesEntity) {
         clothesRepository.delete(clothesEntity);
    }
}
