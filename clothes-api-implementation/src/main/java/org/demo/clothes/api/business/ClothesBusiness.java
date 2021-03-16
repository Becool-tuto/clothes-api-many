package org.demo.clothes.api.business;

import org.demo.clothes.api.exception.BrandNotFoundException;
import org.demo.clothes.api.exception.ClothesAlreadyExistsException;
import org.demo.clothes.api.exception.ClothesNotFoundException;
import org.example.clothes.api.v1.dto.Clothes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ClothesBusiness {

    Clothes viewClothes(String clothesId) throws ClothesNotFoundException;
    Page<Clothes> findClothes(Map<String, Object> filterMap, Pageable pageable);

    Clothes createClothes(Clothes clothes,String brand) throws ClothesAlreadyExistsException, BrandNotFoundException;
    Clothes updateClothes(String clothesId,Clothes clothes, String brand) throws ClothesNotFoundException,BrandNotFoundException;
    void deleteClothes(String clothesId) throws ClothesNotFoundException;
}
