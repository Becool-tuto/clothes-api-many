package org.demo.clothes.api.util;

import org.demo.clothes.api.model.ClothesEntity;
import org.demo.clothes.api.model.TypeEntity;

import java.lang.reflect.Type;
import java.util.UUID;

public class ClothesTestsUtils {

    public static String VALID_CLOTHES_ID = "5cd547a8-866a-11eb-8dcd-0242ac130003";

    public static ClothesEntity createClothesEntity(){
        ClothesEntity clothesEntity = new ClothesEntity();
        clothesEntity.setSize("M");
        clothesEntity.setColor("Blue");
        TypeEntity type = createTypeEntity();
        clothesEntity.setType(type);
        return clothesEntity;
    }

    public static TypeEntity createTypeEntity(){
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setName("T-shirt");
        return typeEntity;
    }
}
