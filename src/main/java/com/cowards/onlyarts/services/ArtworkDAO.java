/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.services;

import com.cowards.onlyarts.repositories.artworks.ArtworkDTO;
import com.cowards.onlyarts.utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ArtworkDAO {

    private static final String GETONE = "SELECT * FROM Artworks WHERE artwork_id = ?";

    private static ArtworkDAO instance;

    private final Connection conn = DBContext.getInstance();

    private ArtworkDAO() {
    }

    public static ArtworkDAO getInstance() {
        if (instance == null) {
            instance = new ArtworkDAO();
        }
        return instance;
    }

    public ArtworkDTO getOne(String id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArtworkDTO artworkDTO = null;
        try {
            stm = conn.prepareStatement(GETONE);
            stm.setString(1, id);
            rs = stm.executeQuery();
            artworkDTO = new ArtworkDTO();
            while (rs.next()) {
                artworkDTO.setArtworkId(rs.getString(1));
                artworkDTO.setOwnerId(rs.getString(2));
                artworkDTO.setCateId(rs.getString(3));
                artworkDTO.setPrivacy(rs.getInt(4));
                artworkDTO.setName(rs.getString(5));
                artworkDTO.setDescription(rs.getString(6));
                artworkDTO.setArtworkImage(rs.getBytes(7));
                artworkDTO.setPrice(rs.getFloat(8));
                artworkDTO.setQuantity(rs.getLong(9));
                artworkDTO.setReleaseDate(rs.getTimestamp(10));
                artworkDTO.setStatus(rs.getString(11));
            }
        } catch (SQLException e) {
            Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Exception found on on getOne() method", e);
        }
        return artworkDTO;
    }
}
