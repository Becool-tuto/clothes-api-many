package org.demo.clothes.api.mapper;

import org.demo.clothes.api.model.BrandEntity;
import org.example.clothes.api.v1.dto.Brand;
import org.example.clothes.api.v1.dto.BrandClothes;
import org.example.clothes.api.v1.dto.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class BrandMapper {

    @Autowired
    ClothesMapper clothesMapper;

    public static Brand asSummaryBrandDto(BrandEntity in){
        Brand brand = new Brand();
        brand.setId(in.getId());
        brand.setName(in.getName());
        return brand;
    }

    public BrandClothes asFullBrandClothesDto(BrandEntity in){
        BrandClothes brandClothes = new BrandClothes();
        brandClothes.setName(in.getName());
        List<Clothes> clothes = in.getBrandsClothes().stream().map(clothesEntity -> clothesMapper.clothesEntityToClothes(clothesEntity)).collect(Collectors.toList());
        brandClothes.setClothes(clothes);
        return brandClothes;
    }
}
