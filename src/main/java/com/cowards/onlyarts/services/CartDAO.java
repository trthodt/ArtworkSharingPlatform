/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.services;

import com.cowards.onlyarts.utils.DBContext;
import java.sql.Connection;

/**
 *
 * @author Admin
 */
public class CartDAO {
    
    private final Connection conn = DBContext.getInstance();
    
    private static CartDAO instance;
    
    private CartDAO() {
    }
    
    public static CartDAO getInstance() {
        if (instance == null) {
            instance = new CartDAO();
        }
        return instance;
    }
    
    
}
