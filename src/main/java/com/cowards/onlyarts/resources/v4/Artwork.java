package com.cowards.onlyarts.resources.v4;

import com.cowards.onlyarts.models.artworks.ArtworkDAO;
import com.cowards.onlyarts.models.artworks.ArtworkDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("v4/artwork")
public class Artwork {
    private final ArtworkDAO artworkDAO = ArtworkDAO.getInstance();
    
    
    @GET
    @Path("favorite")
    public Response getFavoriteArtwork(@Context HttpHeaders header){
        List<ArtworkDTO> favoriteArtwork = new ArrayList<>();
        MultivaluedMap<String, String> headerParam = header.getRequestHeaders();
        String tokenString = headerParam.getFirst("token");
        String userId = "US0001";
        try {
            favoriteArtwork = artworkDAO.getFavoriteArtwork(userId);
        } catch (SQLException ex) {
            Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
        }
        return !favoriteArtwork.isEmpty() ?
                Response.ok(favoriteArtwork, MediaType.APPLICATION_JSON).build():
                Response.status(Response.Status.NO_CONTENT).build();
    }
}
