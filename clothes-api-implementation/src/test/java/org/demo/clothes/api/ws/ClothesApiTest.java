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
import org.springframework.boot.web.server.LocalServerPort;

public  abstract class ClothesApiTest extends ClothesAppTestServer {

    private static final Logger logger = LoggerFactory.getLogger(ClothesApiTest.class);

    protected static final String RESSOURCE_PATH = "/clothes";

    protected static final String BASE_PATH= "/clothesapi/v1";

    @Autowired
    protected ClothesRepository clothesRepository;

    @LocalServerPort
    protected int port;


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
