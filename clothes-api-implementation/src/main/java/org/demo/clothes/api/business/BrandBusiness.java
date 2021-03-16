package org.demo.clothes.api.business;

import org.demo.clothes.api.exception.BrandNotFoundException;
import org.example.clothes.api.v1.dto.Brand;
import org.example.clothes.api.v1.dto.BrandClothes;

import java.util.List;

public interface BrandBusiness {
    List<Brand> findBrands();
    List<BrandClothes> viewClothes(List<String> brands);
}
