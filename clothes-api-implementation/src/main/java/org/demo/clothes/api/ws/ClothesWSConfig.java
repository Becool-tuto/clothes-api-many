package org.demo.clothes.api.ws;

import org.demo.clothes.api.filter.AuthenticationFilter;
import org.example.clothes.api.v1.BrandsApi;
import org.example.clothes.api.v1.ClothesApi;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("clothesapi/v1")
public class ClothesWSConfig extends ResourceConfig {

    public ClothesWSConfig(){
        registerEndpoints();
    }

    private void registerEndpoints() {
         register(BrandsApi.class);
         register(ClothesApi.class);
         register(AuthenticationFilter.class);
    }
}
