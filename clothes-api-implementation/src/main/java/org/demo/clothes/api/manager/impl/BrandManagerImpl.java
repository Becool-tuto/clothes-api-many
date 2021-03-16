package org.demo.clothes.api.manager.impl;

import org.demo.clothes.api.exception.BrandNotFoundException;
import org.demo.clothes.api.manager.BrandManager;
import org.demo.clothes.api.model.BrandEntity;
import org.demo.clothes.api.repository.BrandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandManagerImpl implements BrandManager {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<BrandEntity> findAll() {
        return brandRepository.findAll();
    }


    @Override
    public BrandEntity findByName(String name) throws BrandNotFoundException {
        Optional<BrandEntity> brandEntity = brandRepository.findByName(name);
        if (brandEntity.isPresent()){
            return brandEntity.get();
        }
        else throw new BrandNotFoundException();
    }
}
