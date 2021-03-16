package org.demo.clothes.api.mapper;

import org.demo.clothes.api.model.ClothesEntity;
import org.example.clothes.api.v1.dto.Clothes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ClothesMapper {
    @Mapping(target = "type", ignore = true)
    ClothesEntity clothesToClothesEntity(Clothes clothes);
    @Mapping(target = "type", ignore = true)
    Clothes clothesEntityToClothes(ClothesEntity clothesEntity);
}
