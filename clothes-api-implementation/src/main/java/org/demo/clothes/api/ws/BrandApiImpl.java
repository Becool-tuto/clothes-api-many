package org.demo.clothes.api.ws;

import org.demo.clothes.api.business.BrandBusiness;
import org.example.clothes.api.v1.BrandsApi;
import org.example.clothes.api.v1.dto.Brand;
import org.example.clothes.api.v1.dto.BrandClothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.*;

@Controller
public class BrandApiImpl implements BrandsApi {

    @Autowired
    BrandBusiness brandBusiness;

    @Override
    public Response findBrands() {
        List<Brand> brandList = brandBusiness.findBrands();
        if(brandList!=null && !brandList.isEmpty()){
            return Response.status(Response.Status.OK).entity(brandList).build();
        }
        return Response.noContent().build();
    }

    @Override
    public Response getClothesByBrands(String brandIds) {
        List<BrandClothes> brandClothes=null;
        List<String> brands = new ArrayList<>();
        if(!brandIds.isEmpty()){
            if(brandIds.contains(",")){
                brands = Arrays.asList(brandIds.split(","));
            }
            else {
                brands = Collections.singletonList(brandIds);
            }
            brandClothes= brandBusiness.viewClothes(brands);
        }
        if (brandClothes==null)
            Response.status(Response.Status.NO_CONTENT).entity(String.format("no clothes found"));
        return Response.status(Response.Status.OK).entity(brandClothes).build();
    }
}
