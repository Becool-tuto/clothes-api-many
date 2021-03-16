package org.demo.clothes.api.business.impl;

import antlr.StringUtils;
import org.demo.clothes.api.business.BrandBusiness;
import org.demo.clothes.api.exception.BrandNotFoundException;
import org.demo.clothes.api.manager.BrandManager;
import org.demo.clothes.api.manager.impl.BrandManagerImpl;
import org.demo.clothes.api.mapper.BrandMapper;
import org.demo.clothes.api.model.BrandEntity;
import org.example.clothes.api.v1.dto.Brand;
import org.example.clothes.api.v1.dto.BrandClothes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandBusinessImpl implements BrandBusiness {

    private static final Logger logger = LoggerFactory.getLogger(BrandManagerImpl.class);
    @Autowired
    BrandManager brandManager;
    @Autowired
    BrandMapper brandMapper;
    @Override
    public List<Brand> findBrands() {
        List<BrandEntity> brandEntities;
        List<Brand> brands = null;
        brandEntities = brandManager.findAll();
        if(brandEntities!=null && !brandEntities.isEmpty()){
            brands = brandEntities.stream().map(BrandMapper::asSummaryBrandDto).collect(Collectors.toList());
        }
        return brands;
    }

    @Override
    public List<BrandClothes> viewClothes(List<String> brands) {
        List<BrandClothes> brandClothes = null;
        List<BrandEntity> brandEntities = new ArrayList<>();
        for (String brand: brands){
            try {
                brandEntities.add(brandManager.findByName(brand));
            } catch (BrandNotFoundException e) {
                logger.info(String.format("clothes not found for brand < %s >",brand));
            }
        }
        if(!brandEntities.isEmpty())
        {
            brandClothes = brandEntities.stream().map(brand ->brandMapper.asFullBrandClothesDto(brand)).collect(Collectors.toList());
        }
        return brandClothes;
    }
}
