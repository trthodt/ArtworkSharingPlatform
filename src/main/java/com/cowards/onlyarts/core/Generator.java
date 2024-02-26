/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.core;

import java.util.UUID;

/**
 *
 * @author Admin
 */
public class Generator {

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString()
                .replaceAll("-", "")
                .replaceAll("\\D", "")
                .substring(0, 20);
        return uuidStr;
    }
}
