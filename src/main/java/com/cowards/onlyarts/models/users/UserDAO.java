package com.cowards.onlyarts.models.users;

import com.cowards.onlyarts.utils.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static UserDAO instance;
    private static final String GETALLUSER = "SELECT [user_id]"
            + ",[role_id],[first_name],[last_name],[avatar],[phone],[email]"
            + ",[address],[join_date],[bio],[status],[password]"
            + " FROM [OnlyArts].[dbo].[Users]";
    private static final String GETUSER = "SELECT "
            + "[role_id],[first_name],[last_name],[avatar],[phone],[email]"
            + ",[address],[join_date],[bio],[status],[password]"
            + " FROM [OnlyArts].[dbo].[Users] WHERE user_id = ?";
    private static final String UPDATEUSER = "UPDATE [OnlyArts].[dbo].[Users] "
            + "SET [first_name] = ?,[last_name] =? ,[phone] = ?,[email] = ?,[address] = ?,[bio] = ? "
            + "WHERE user_id = ? ";
    private static final String FOLLOWUSER = "INSERT INTO [dbo].[Followings] "
            + "([user_id],[followed_user_id]) VALUES ( ?, ?)";
    private static final String UNFOLLOWUSER = "DELETE FROM [dbo].[Followings] "
            + "WHERE user_id = ? AND followed_user_id = ?";
    private static final String GETFOLLOWING = "SELECT tb2.[user_id],[role_id],"
            + "[first_name],[last_name],[avatar],[phone],[email],[address],"
            + "[join_date],[bio],[status],[password] "
            + "FROM [OnlyArts].[dbo].[Followings] tb1 "
            + "RIGHT JOIN [OnlyArts].[dbo].[Users] tb2 "
            + "ON tb1.[followed_user_id]  = tb2.[user_id] "
            + "WHERE tb1.[user_id] = ?";
    private static final String GETFOLLOWER = "SELECT tb2.[user_id],[role_id],"
            + "[first_name],[last_name],[avatar],[phone],[email],[address],"
            + "[join_date],[bio],[status],[password] "
            + "FROM [OnlyArts].[dbo].[Followings] tb1 "
            + "RIGHT JOIN [OnlyArts].[dbo].[Users] tb2 "
            + "ON tb1.[user_id]  = tb2.[user_id] "
            + "WHERE tb1.[followed_user_id] = ?";
    private static final String COUNTFOLLOWING = "SELECT COUNT([followed_user_id]) "
            + "AS [count] FROM [OnlyArts].[dbo].[Followings] "
            + "WHERE [user_id] = ?";
    private static final String COUNTFOLLOWER = "SELECT COUNT([user_id]) AS [count] "
            + "FROM [OnlyArts].[dbo].[Followings] "
            + "WHERE [followed_user_id] = ?";
    private static final String CHECKFOLLOW = "SELECT [user_id],[followed_user_id] "
            + "FROM [OnlyArts].[dbo].[Followings]"
            + "WHERE [user_id] = ? AND [followed_user_id] = ?";
    
    
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public static List<UserDTO> getAllUser() throws SQLException {
        List<UserDTO> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETALLUSER);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString("user_id");
                    String roleId = rs.getString("role_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String avatar = rs.getString("avatar");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    Date joinDate = rs.getDate("join_date");
                    String bio = rs.getString("bio");
                    String status = rs.getString("status");
                    String password = "*****";
                    userList.add(new UserDTO(userId, roleId, firstName, lastName, avatar, phone, email, address, joinDate, bio, status, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return userList;
    }

        public UserDTO getUserProfile(String userId) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETUSER);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String roleId = rs.getString("role_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String avatar = rs.getString("avatar");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    Date joinDate = rs.getDate("join_date");
                    String bio = rs.getString("bio");
                    String status = rs.getString("status");
                    String password = rs.getString("password");
                    user = new UserDTO(userId, roleId, firstName, lastName, avatar, phone, email, address, joinDate, bio, status, password);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
        

    public static boolean updateUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEUSER);
                ptm.setString(1, user.getFirstName());
                ptm.setString(2, user.getLastName());
                ptm.setString(3, user.getPhone());
                ptm.setString(4, user.getEmail());
                ptm.setString(5, user.getAddress());
                ptm.setString(6, user.getBio());
                ptm.setString(7, user.getUserId());
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean followUser(String loginUser, String userId) throws SQLException {
        boolean checkFollow = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(FOLLOWUSER);
                ptm.setString(1, loginUser);
                ptm.setString(2, userId);
                checkFollow = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkFollow;
    }

    public boolean unfollowUser(String loginUser, String userId) throws SQLException {
        boolean checkFollow = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UNFOLLOWUSER);
                ptm.setString(1, loginUser);
                ptm.setString(2, userId);
                checkFollow = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkFollow;
    }

    public List<UserDTO> getFollowing(String userId) throws SQLException {
        List<UserDTO> followingList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETFOLLOWING);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String following_userId = rs.getString("user_id");
                    String roleId = rs.getString("role_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String avatar = rs.getString("avatar");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    Date joinDate = rs.getDate("join_date");
                    String bio = rs.getString("bio");
                    String status = rs.getString("status");
                    String password = "*****";
                    followingList.add(new UserDTO(following_userId, roleId, firstName, lastName, avatar, phone, email, address, joinDate, bio, status, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        return followingList;
    }

    public List<UserDTO> getFollower(String userId) throws SQLException {
        List<UserDTO> followerList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETFOLLOWER);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String following_userId = rs.getString("user_id");
                    String roleId = rs.getString("role_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String avatar = rs.getString("avatar");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    Date joinDate = rs.getDate("join_date");
                    String bio = rs.getString("bio");
                    String status = rs.getString("status");
                    String password = "*****";
                    followerList.add(new UserDTO(following_userId, roleId, firstName, lastName, avatar, phone, email, address, joinDate, bio, status, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return followerList;
    }

    public int countFollowing(String userId) throws SQLException {
        int numberoffolowing = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNTFOLLOWING);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    numberoffolowing = rs.getInt("count");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        return numberoffolowing;
    }

    public int countFollower(String userId) throws SQLException {
        int numberoffolower = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNTFOLLOWER);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    numberoffolower = rs.getInt("count");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return numberoffolower;
    }

    public boolean checkFollow(String loginUserId, String userId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECKFOLLOW);
                ptm.setString(1, loginUserId);
                ptm.setString(2, userId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }



}
