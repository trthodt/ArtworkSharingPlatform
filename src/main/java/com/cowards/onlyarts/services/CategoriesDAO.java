/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.services;

import com.cowards.onlyarts.repositories.categories.CategoriesDTO;
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
public class CategoriesDAO {

    private static final String GETONEBYNAME = "SELECT * FROM Categories WHERE cate_name = ?";

    private static CategoriesDAO instance;

    private final Connection conn = DBContext.getInstance();

    private CategoriesDAO() {
    }

    public static CategoriesDAO getInstance() {
        if (instance == null) {
            instance = new CategoriesDAO();
        }
        return instance;
    }

    public CategoriesDTO getOneByName(String cateName) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        CategoriesDTO cdto = null;
        try {
            stm = conn.prepareStatement(GETONEBYNAME);
            stm.setString(1, cateName);
            rs = stm.executeQuery();
            cdto = new CategoriesDTO();
            while (rs.next()) {
                cdto.setCateId(rs.getString(1));
                cdto.setCateName(rs.getString(2));
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriesDAO.class.getName()).log(Level.SEVERE, "Exception found on getOneByName() method", e);
        }
        return cdto;
    }
}
