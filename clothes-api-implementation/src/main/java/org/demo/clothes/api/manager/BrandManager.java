package org.demo.clothes.api.manager;

import org.demo.clothes.api.exception.BrandNotFoundException;
import org.demo.clothes.api.model.BrandEntity;

import java.util.List;

public interface BrandManager {
    List<BrandEntity> findAll();
    BrandEntity findByName(String name) throws BrandNotFoundException;
}
