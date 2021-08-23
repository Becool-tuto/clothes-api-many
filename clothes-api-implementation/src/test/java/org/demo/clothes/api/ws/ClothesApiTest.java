package org.demo.clothes.api.ws;

import io.restassured.RestAssured;
import org.demo.clothes.api.ClothesAppTestServer;
import org.demo.clothes.api.repository.ClothesRepository;
import org.demo.clothes.api.util.ClothesTestsUtils;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public  abstract class ClothesApiTest extends ClothesAppTestServer {

    private static final Logger logger = LoggerFactory.getLogger(ClothesApiTest.class);

    protected static final String RESSOURCE_PATH = "localhost:8080/clothesapi/v1/clothes";

    @Autowired
    protected ClothesRepository clothesRepository;

    @Before
    public void setUpRestAssured(){
        RestAssured.port = 8080;
        RestAssured.basePath = "clothesapi/v1";
    }

    @Before
    public void insertSomeData(){
       logger.info(":: begin insertSomeData::");
        clothesRepository.save(ClothesTestsUtils.createClothesEntity());
    }

    @After
    public void clearData(){
        clothesRepository.deleteAll();
    }
}
