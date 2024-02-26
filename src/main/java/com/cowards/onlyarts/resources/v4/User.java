package com.cowards.onlyarts.resources.v4;

import com.cowards.onlyarts.models.users.UserDAO;
import com.cowards.onlyarts.models.users.UserDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
@Path("v4/user")
public class User {
    
    private final UserDAO dao = UserDAO.getInstance();
    
    @GET
    public Response getUserProfile(@Context HttpHeaders header){
        UserDTO user = null;
        MultivaluedMap<String, String> headerParam = header.getRequestHeaders();
        String tokenString = headerParam.getFirst("token");
        String userId = "US0001";
        try {
            user = dao.getUserProfile(userId);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user!= null ?
                Response.ok(user, MediaType.APPLICATION_JSON).build():
                Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{user_id}")
    public Response getUser(@PathParam("user_id") String userId){
        UserDTO user = null;
        try {
            user = dao.getUserProfile(userId);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user!=null ? 
                Response.ok(user, MediaType.APPLICATION_JSON).build():
                Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateUser(UserDTO user) {
        HashMap<String,Object> result = new HashMap<>();
        try {
            boolean checkUpdate = dao.updateUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return !result.isEmpty() ? 
                Response.status(Response.Status.CREATED).build():
                Response.status(Response.Status.NO_CONTENT).build();
    }
}
