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
    private static final String UPDATE = "UPDATE Artworks SET cate_id = ?, privacy = ?, name = ?, description = ?, "
            + "artwork_image = ?, price = ?, quantity = ?, released_date = ?, status = ? WHERE artwork_id = ?";

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
                artworkDTO.setReleasedDate(rs.getTimestamp(10));
                artworkDTO.setStatus(rs.getString(11));
            }
        } catch (SQLException e) {
            Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Exception found on getOne() method", e);
        }
        return artworkDTO;
    }

    public void update(ArtworkDTO artworkDTO) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(UPDATE);
            stm.setString(1, artworkDTO.getCateId());
            stm.setInt(2, artworkDTO.getPrivacy());
            stm.setString(3, artworkDTO.getName());
            stm.setString(4, artworkDTO.getDescription());
            stm.setBytes(5, artworkDTO.getArtworkImage());
            stm.setFloat(6, artworkDTO.getPrice());
            stm.setLong(7, artworkDTO.getQuantity());
            stm.setTimestamp(8, artworkDTO.getReleasedDate());
            stm.setString(9, artworkDTO.getStatus());
            stm.setString(10, artworkDTO.getArtworkId());
            stm.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Exception found on update() method", e);
        }
    }
}
