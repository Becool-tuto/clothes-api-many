package org.demo.clothes.api.repository;

import org.demo.clothes.api.ClothesAppTestServer;
import org.demo.clothes.api.model.TypeEntity;
import org.demo.clothes.api.util.ClothesTestsUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TypeRepositoryTest extends ClothesAppTestServer {

    @Autowired
    TypeRepository typeRepository;

    @Test
    @Transactional(transactionManager = "clothesTransactionManager")
    public void givenTypeEntityRepository_whenSaveAndRetreiveEntity_thenOK(){
        TypeEntity typeEntity = typeRepository.save(ClothesTestsUtils.createTypeEntity());
        Optional<TypeEntity> foundEntity = typeRepository.findById(typeEntity.getId());
        assertNotNull(foundEntity);
        assertEquals(typeEntity.getId(), foundEntity.get().getId());


    }

}
