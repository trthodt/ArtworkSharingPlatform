/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v3;

import com.cowards.onlyarts.repositories.artworks.ArtworkDTO;
import com.cowards.onlyarts.services.ArtworkDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Path("/v3/artworks/update")
public class UpdateArtwork {

    private final ArtworkDAO artworkDao = ArtworkDAO.getInstance();

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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ArtworkDTO adto) {
        try {
            artworkDao.update(adto);
            ArtworkDTO artworkDTO = artworkDao.getOne(adto.getArtworkId());
            return Response.ok(artworkDTO).build();
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }
}
