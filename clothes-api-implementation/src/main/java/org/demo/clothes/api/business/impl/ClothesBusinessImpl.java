package org.demo.clothes.api.business.impl;

import org.demo.clothes.api.business.ClothesBusiness;
import org.demo.clothes.api.exception.BrandNotFoundException;
import org.demo.clothes.api.exception.ClothesAlreadyExistsException;
import org.demo.clothes.api.exception.ClothesNotFoundException;
import org.demo.clothes.api.manager.BrandManager;
import org.demo.clothes.api.manager.ClothesManager;
import org.demo.clothes.api.mapper.ClothesMapper;
import org.demo.clothes.api.model.BrandEntity;
import org.demo.clothes.api.model.ClothesEntity;
import org.demo.clothes.api.repository.filter.ClothesSpecification;
import org.example.clothes.api.v1.dto.Clothes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ClothesBusinessImpl implements ClothesBusiness {

    private static final Logger logger = LoggerFactory.getLogger(ClothesBusinessImpl.class);

    @Autowired
    private ClothesManager clothesManager;

    @Autowired
    private ClothesMapper clothesMapper;

    @Autowired
    private BrandManager brandManager;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public Clothes viewClothes(String clothesId) throws ClothesNotFoundException {
        Clothes clothes = null;
        clothes = clothesManager.findById(clothesId).map(clothesEntity -> clothesMapper.clothesEntityToClothes(clothesEntity))
                                                    .orElseThrow(ClothesNotFoundException::new);
        return clothes;
    }

    @Override
    public Page<Clothes> findClothes(Map<String, Object> filterMap, Pageable pageable) {
        ClothesSpecification specification = new ClothesSpecification(filterMap);
        Page<ClothesEntity> page = clothesManager.findAll(specification,pageable);
        Page<Clothes> clothesPage = null;
        if(Objects.nonNull(page)){
            clothesPage = PageableExecutionUtils.getPage(page.getContent().stream().map(agreementEntity-> clothesMapper.clothesEntityToClothes(agreementEntity)).collect(Collectors.toList()), pageable,page::getTotalElements);
        }
        return clothesPage;
    }

    @Override
    public Clothes createClothes(Clothes clothes, String brand) throws ClothesAlreadyExistsException, BrandNotFoundException {
        logger.info("[Clothes API] clothes.createClothes check if the clothes already exists in database");
        this.checkIfClothesAlreadyExist(clothes);
        ClothesEntity clothesEntity = clothesMapper.clothesToClothesEntity(clothes);
        BrandEntity brandEntity = null;
        try{
            brandEntity = brandManager.findByName(brand);
        }catch (BrandNotFoundException ex){
            brandEntity = brandManager.findByName("default");
        }
        Set<BrandEntity> brandEntities = new HashSet<>();
        brandEntities.add(brandEntity);
        clothesEntity.setClothesBrands(brandEntities);

        clothesManager.saveClothes(clothesEntity);
        return clothesMapper.clothesEntityToClothes(clothesEntity);
    }

    @Override
    public Clothes updateClothes(String clothesId, Clothes clothes, String brand) throws ClothesNotFoundException,BrandNotFoundException {
        ClothesEntity clothesEntity=null;
        logger.info("[Clothes API] call of ClothesBusiness method updateClothes with clothesUniqueId : {} and clothes input {}", clothesId,clothes);
        logger.info("[Clothes API] check if clothes by given Id : {} exists",clothesId);
        clothesEntity = clothesManager.findById(clothesId).orElseThrow(ClothesNotFoundException::new);
        BrandEntity brandEntity = null;
        try {
            brandEntity = brandManager.findByName(brand);
        }catch (BrandNotFoundException ex){
            logger.info(":: brand not found .. add default brand");
            brandEntity= brandManager.findByName("default");

        }
        Set<BrandEntity> brandEntities = clothesEntity.getClothesBrands()!=null? clothesEntity.getClothesBrands():new HashSet<>();
        brandEntities.add(brandEntity);
        clothesEntity.setClothesBrands(brandEntities);
        clothesManager.updateClothes(clothesEntity);
        return clothesMapper.clothesEntityToClothes(clothesEntity);
    }

    @Override
    public void deleteClothes(String clothesId) throws ClothesNotFoundException {
        Optional<ClothesEntity> clothes = clothesManager.findById(clothesId);
        if(clothes.isPresent()){
            clothesManager.deleteClothes(clothes.get());
        }
        else {
            throw new ClothesNotFoundException(String.format("Clothes with the given id < %s > is not found ",clothesId));
        }
    }

    private void checkIfClothesAlreadyExist(Clothes clothes) throws ClothesAlreadyExistsException{
        Map<String, String> filterMap = new HashMap<>();
        Optional.ofNullable(clothes.getColor()).ifPresent(element-> filterMap.put("color",element));
        Optional.ofNullable(clothes.getSize()).ifPresent(element -> filterMap.put("size",element));
        Optional.ofNullable(clothes.getType()).ifPresent(element-> filterMap.put("type",element));
        Specification<ClothesEntity> specification = new ClothesSpecification(filterMap);
        List<ClothesEntity> clothesEntityList = clothesManager.findAll(specification);

        if(!clothesEntityList.isEmpty()){
            throw new ClothesAlreadyExistsException();
        }

    }
}
