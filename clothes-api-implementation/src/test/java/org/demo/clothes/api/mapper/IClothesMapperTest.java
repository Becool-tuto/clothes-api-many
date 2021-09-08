package org.demo.clothes.api.mapper;


import org.demo.clothes.api.model.ClothesEntity;
import org.example.clothes.api.v1.dto.Clothes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.demo.clothes.api.util.ClothesTestsUtils.createClothesEntity;
public class IClothesMapperTest {

    private IClothesMapper clothesMapper = Mappers.getMapper(IClothesMapper.class);

    @Test
    public void testClothesMapping(){
        ClothesEntity clothesEntity = createClothesEntity();
        Clothes clothes = clothesMapper.toClothes(clothesEntity);
        Assertions.assertNotNull(clothes);
        Assertions.assertEquals(clothesEntity.getColor(),clothes.getColor());
        Assertions.assertEquals(clothes.getType(),clothesEntity.getType().getName());
    }

    @Test
    public void testClothesEntityMapping(){
        Clothes clothes = new Clothes();
        clothes.setColor("RED");
        clothes.setSize("M");
        clothes.setType("t-shirt");

        ClothesEntity clothesEntity = clothesMapper.toClothesEntity(clothes);
        Assertions.assertEquals(clothesEntity.getColor(),clothes.getColor());
    }
}
