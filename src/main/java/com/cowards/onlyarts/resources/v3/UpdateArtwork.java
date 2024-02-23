/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v3;

import com.cowards.onlyarts.repositories.artworks.ArtworkDTO;
import com.cowards.onlyarts.repositories.categories.CategoriesDTO;
import com.cowards.onlyarts.services.ArtworkDAO;
import com.cowards.onlyarts.services.CategoriesDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
@Path("/v3/artworks/update")
public class UpdateArtwork {

    private final ArtworkDAO artworkDao = ArtworkDAO.getInstance();
    private final CategoriesDAO categoriesDao = CategoriesDAO.getInstance();

    @GET
    @Path("{artworkId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("artworkId") String artworkId) {
        try {
            ArtworkDTO artworkDTO = artworkDao.getOne(artworkId);
            return Response.ok(artworkDTO).build();
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }

//    @POST
//    @Path("{artworkId}")
//    @Consumes("application/x-www-form-urlencoded")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response update(@PathParam("artworkId") String artworkId, @FormParam("cateName") String cateName, @FormParam("privacy") int privacy,
//            @FormParam("name") String name, @FormParam("description") String description, @FormParam("artwork_image") byte[] artworkImage,
//            @FormParam("price") float price, @FormParam("quantity") long quantity, @FormParam("released_date") Timestamp releasedDate,
//            @FormParam("status") String status) {
//        try {
//            ArtworkDTO artworkDTO = artworkDao.getOne(artworkId);
//
//            CategoriesDTO cdto = categoriesDao.getOneByName(cateName);
//            String cateId = cdto.getCateId();
//            artworkDTO.setCateId(cateId);
//            
//            artworkDTO.setPrivacy(privacy);
//            
//            artworkDTO.setName(name);
//            
//            artworkDTO.setDescription(description);
//            
//            artworkDTO.setArtworkImage(artworkImage);
//            
//            artworkDTO.setPrice(price);
//            
//            artworkDTO.setQuantity(quantity);
//            
//            artworkDTO.setReleasedDate(releasedDate);
//            
//            artworkDTO.setStatus(status);
//
//            artworkDao.update(artworkDTO);
//            
//            return Response.ok(artworkDTO, MediaType.APPLICATION_JSON).build();
//        } catch (Exception e) {
//            return Response.ok(e, MediaType.APPLICATION_JSON).build();
//        }
//    }
    
    @PUT
    @Path("{artworkId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ArtworkDTO adto) {
        try {
            boolean result = artworkDao.update(adto);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }
}
