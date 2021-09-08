package org.demo.clothes.api.mapper;

import org.demo.clothes.api.model.ClothesEntity;
import org.demo.clothes.api.repository.TypeRepository;
import org.example.clothes.api.v1.dto.Clothes;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IClothesMapper {



    @Mapping(target = "id", source = "id")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "color", source = "color")
    @Mapping(target = "type", source = "type.name")
    public Clothes toClothes(ClothesEntity clothesEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "size", source = "size")
    @Mapping(target = "color", source = "color")
    @Mapping(target = "type", ignore = true)
    public ClothesEntity toClothesEntity(Clothes clothes);

}
