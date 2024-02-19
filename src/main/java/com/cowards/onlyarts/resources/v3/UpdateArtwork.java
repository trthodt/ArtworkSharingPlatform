/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v3;

import com.cowards.onlyarts.services.ArtworkDAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Path("/v3/artworks")
public class UpdateArtwork {

    private final ArtworkDAO artworkDao = ArtworkDAO.getInstance();

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArtworkDTO getOne(String artworkId) {
//        try {
//            ArtworkDTO artworkDTO = artworkDao.getOne("AW0001");
//            System.out.println(artworkDTO);
//            return artworkDTO;
////            return Response.ok(artworkDTO, MediaType.APPLICATION_JSON).build();
//        } catch (Exception e) {
////            return Response.ok(e, MediaType.APPLICATION_JSON).build();
//            return null;
//        }
//    }
//    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArtworkDTO getArt() {
//        return new ArtworkDTO("1", "1", "1", 0, "1", "1", null, 10, 10, null, "1");
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok("hjhj").build();
    }
}
