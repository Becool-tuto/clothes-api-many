package org.demo.clothes.api.mapper;

import org.demo.clothes.api.model.ClothesEntity;
import org.demo.clothes.api.model.TypeEntity;
import org.demo.clothes.api.repository.TypeRepository;
import org.example.clothes.api.v1.dto.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ClothesMapper{

    @Autowired
    TypeRepository typeRepository;

    public ClothesEntity clothesToClothesEntity(Clothes clothes) {
        if (clothes == null){
            return null;
        }
        ClothesEntity clothesEntity = new ClothesEntity();
        clothesEntity.setId(clothes.getId());
        clothesEntity.setSize(clothes.getSize());
        clothesEntity.setColor(clothes.getColor());
        TypeEntity typeEntity = typeRepository.findByName(clothes.getType());
        clothesEntity.setType(typeEntity);
        return clothesEntity;

    }

    public Clothes clothesEntityToClothes(ClothesEntity clothesEntity) {
        if(clothesEntity == null){
            return null;
        }
    Clothes clothes = new Clothes();
        clothes.setId(clothesEntity.getId());
        clothes.setSize(clothesEntity.getSize());
        clothes.setColor(clothesEntity.getColor());
        clothes.setType(clothesEntity.getType().getName());
    return clothes;
    }
}
