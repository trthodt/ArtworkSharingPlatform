
package com.cowards.onlyarts.resources.v4;

import com.cowards.onlyarts.models.users.UserDAO;
import com.cowards.onlyarts.models.users.UserDTO;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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

/**
 *
 * @author truon
 */
@Path("v4/follow")
public class Following {
    private final UserDAO dao = UserDAO.getInstance();
    
    @GET
    @Path("following")
    public Response getFollowing(@Context HttpHeaders header){
        List<UserDTO> followingList = new ArrayList<>();
        MultivaluedMap<String, String> headerParam = header.getRequestHeaders();
        String tokenString = headerParam.getFirst("token");
        try {
            String userId = "US0001";
            followingList = dao.getFollowing(userId);
        } catch (SQLException ex) {
            Logger.getLogger(Following.class.getName()).log(Level.SEVERE, null, ex);
        }
        return !followingList.isEmpty() ?
                    Response.ok(followingList, MediaType.APPLICATION_JSON).build() :
                    Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("countfollowing")
    public Response countFollowing(@Context HttpHeaders header){
        int numberoffolowing = 0;
        MultivaluedMap<String, String> headerParam = header.getRequestHeaders();
        String tokenString = headerParam.getFirst("token");
        try {
            String userId = "US0001";
            numberoffolowing = dao.countFollowing(userId);
        } catch (SQLException ex) {
            Logger.getLogger(Following.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok(numberoffolowing, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("follower")
    public Response getFollower(@Context HttpHeaders header){
        List<UserDTO> followerList = new ArrayList<>();
        MultivaluedMap<String, String> headerParam = header.getRequestHeaders();
        String tokenString = headerParam.getFirst("token");
        try {
            String userId = "US0009";
            followerList = dao.getFollower(userId);
        } catch (SQLException ex) {
            Logger.getLogger(Following.class.getName()).log(Level.SEVERE, null, ex);
        }
        return !followerList.isEmpty() ?
                    Response.ok(followerList, MediaType.APPLICATION_JSON).build() :
                    Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("countfollower")
    public Response countFollower(@Context HttpHeaders header){
        int numberoffolower = 0;
        MultivaluedMap<String, String> headerParam = header.getRequestHeaders();
        String tokenString = headerParam.getFirst("token");
        try {
            String userId = "US0001";
            numberoffolower = dao.countFollower(userId);
        } catch (SQLException ex) {
            Logger.getLogger(Following.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok(numberoffolower, MediaType.APPLICATION_JSON).build();
    }
    
    
    @POST
    @Path("{user_id}")
    public Response followUser(@PathParam("user_id") String userId,@Context HttpHeaders header){
        boolean checkFollow = false;
        MultivaluedMap<String, String> headerParam = header.getRequestHeaders();
        String tokenString = headerParam.getFirst("token");
        String loginUser = "US0001";
        try {
            checkFollow = dao.followUser(loginUser,userId);
        } catch (SQLException ex) {
            Logger.getLogger(Following.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkFollow ? Response.status(Response.Status.CREATED).build():
                Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("{user_id}")
    public Response unfollowUser(@PathParam("user_id") String userId,@Context HttpHeaders header){
        boolean checkFollow = false;
        MultivaluedMap<String, String> headerParam = header.getRequestHeaders();
        String tokenString = headerParam.getFirst("token");
        String loginUser = "US0001";
        try {
            checkFollow = dao.unfollowUser(loginUser,userId);
        } catch (SQLException ex) {
            Logger.getLogger(Following.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkFollow ? Response.status(Response.Status.CREATED).build():
                Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("check/{user_id}")
    public Response checkFollow(@PathParam("user_id") String userId, @Context HttpHeaders header){
        boolean checkFollow = false;
        MultivaluedMap<String, String> headerParam = header.getRequestHeaders();
        String tokenString = headerParam.getFirst("token");
        String loginUserId = "US0001";
        try {
            checkFollow = dao.checkFollow(loginUserId,userId);
        } catch (SQLException ex) {
            Logger.getLogger(Following.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok(checkFollow, MediaType.APPLICATION_JSON).build();
    }
    
}
