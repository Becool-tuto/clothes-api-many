package org.demo.clothes.api.ws;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;

public class GetOperationsTest extends ClothesApiTest{

    @Test
    @Transactional(transactionManager = "clothesTransactionManager")
     public void getWithknownId(){
        String existingId = clothesRepository.findAll().get(0).getId();
        given().basePath(BASE_PATH).port(port).pathParam("id",existingId)
                .when().get(RESSOURCE_PATH+"/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType(ContentType.JSON);
    }

   @Test
   @Transactional(transactionManager = "clothesTransactionManager")
   public void getBrands(){
       given().basePath(BASE_PATH).port(port)
               .when().get(RESSOURCE_BRAND_PATH)
               .then().statusCode(200)
               .and().assertThat().contentType(ContentType.JSON)
               .body(matchesJsonSchemaInClasspath("schema/brands.json"));
   }

}
