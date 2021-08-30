package org.demo.clothes.api.repository;

import org.demo.clothes.api.ClothesAppTestServer;
import org.demo.clothes.api.model.ClothesEntity;
import org.demo.clothes.api.util.ClothesTestsUtils;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ClothesRepositoryTest extends ClothesAppTestServer {

    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private TypeRepository typeRepository;


    @Before
    public void createTypeEntity(){
        typeRepository.save(ClothesTestsUtils.createTypeEntity());
    }


    @Test
    @Transactional(transactionManager = "clothesTransactionManager")
    public void testWhenFindByIDAndNOClothesMatchedThenReturnNothing(){

        clothesRepository.deleteAll();
        Optional<ClothesEntity> clothesEntity = clothesRepository.findById(ClothesTestsUtils.VALID_CLOTHES_ID);
        assertThat(clothesEntity.isPresent()).isFalse();
    }

    @Test
    @Transactional(transactionManager = "clothesTransactionManager")
    public void testWhenFindByIdAndClothesMatchedThenReturnSomething(){
        ClothesEntity clothesEntity = clothesRepository.save(ClothesTestsUtils.createClothesEntity());
        Optional<ClothesEntity> foundEntity = clothesRepository.findById(clothesEntity.getId());
        assertThat(foundEntity.isPresent()).isTrue();
    }
}
