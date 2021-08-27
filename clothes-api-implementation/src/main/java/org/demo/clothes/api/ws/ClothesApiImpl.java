package org.demo.clothes.api.ws;


import org.demo.clothes.api.business.ClothesBusiness;
import org.demo.clothes.api.exception.BrandNotFoundException;
import org.demo.clothes.api.exception.ClothesAlreadyExistsException;
import org.demo.clothes.api.exception.ClothesNotFoundException;
import org.demo.clothes.api.helper.ClothesHelper;
import org.example.clothes.api.v1.ClothesApi;
import org.example.clothes.api.v1.dto.Clothes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;


import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Controller
public class ClothesApiImpl implements ClothesApi {

    private static final Logger logger = LoggerFactory.getLogger(ClothesApiImpl.class);

    @Autowired
    private ClothesBusiness clothesBusiness;
    @Autowired
    private ClothesHelper clothesHelper;
    @Override
    public Response createClothes(@javax.validation.Valid Clothes clothes, String xOriginBrand) {
        try{
            Clothes clothesCreated = clothesBusiness.createClothes(clothes,xOriginBrand);
            return Response.status(Response.Status.CREATED).entity(clothesCreated).build();
        }catch (ClothesAlreadyExistsException | BrandNotFoundException exception){
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("Clothes already exists or brand % not found",xOriginBrand)).build();
        }
    }

    @Override
    public Response deleteClothes(String clothesId) {
        return null;
    }

    @Override
    public Response findClothes(String range, String filter) {
        logger.info("[Clothes API] Get call with range {}, filter {} ",range,filter);
        Map<String,Object> filterMap = clothesHelper.createFilterMap(filter);
        if(filterMap.size() == 0){
            return Response.ok().entity(new ArrayList<>()).build();
        }

        Page<Clothes> clothesPage = clothesBusiness.findClothes(filterMap, PageRequest.of(0,10));
        if(Objects.nonNull(clothesPage) && clothesPage.getTotalElements()!=0){
            return Response.status(clothesPage.isFirst() && clothesPage.isLast() ? Response.Status.OK:Response.Status.PARTIAL_CONTENT)
                           .header("Content-Range",String.format("clothes %d-%d/%d",clothesPage.getNumber()*clothesPage.getNumberOfElements(),clothesPage.getNumberOfElements(),clothesPage.getTotalElements()))
                           .entity(clothesPage.getContent())
                           .build();
        } else {
            return Response.noContent().build();
        }
    }

    @Override
    public Response getClothesByID(String clothesId) {
        try{
            Clothes clothes = clothesBusiness.viewClothes(clothesId);
            return Response.status(Response.Status.OK).entity(clothes).build();
        }catch (ClothesNotFoundException clothesNotFoundException){
            return Response.status(Response.Status.NOT_FOUND).entity(String.format("Clothes with given id < %s > not found ",clothesId)).build();
        }

    }

    @Override
    public Response updateClothes(String clothesId, @javax.validation.Valid Clothes clothes, String xOriginBrand) {
       try{
           clothesBusiness.updateClothes(clothesId,clothes,xOriginBrand);
       }catch (ClothesNotFoundException | BrandNotFoundException ex){
           return Response.status(Response.Status.NOT_FOUND).entity(String.format("Clothes with given id < %s > or brand < %s > not found ",clothesId,xOriginBrand )).build();
       }
        return Response.status(Response.Status.ACCEPTED).build();
    }
}


