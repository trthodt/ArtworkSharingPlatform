/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v3;

import com.cowards.onlyarts.services.ArtworkDAO;
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
@Path("/v3/artworks/delete")
public class DeleteArtwork {
    
    private final ArtworkDAO artworkDAO = ArtworkDAO.getInstance();
    
    @PUT
    @Path("{artworkId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("artworkId") String artworkId) {
        try {
            boolean result = artworkDAO.delete(artworkId);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }
}
