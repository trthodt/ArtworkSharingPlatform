package com.cowards.onlyarts.models.dao;

public class UserDao {
    private static UserDao instance = null ;
    
    private UserDao(){}
    public static UserDao getInstance(){
        if (instance ==null){
            instance = new UserDao();
        }
        return instance;
    }
}
