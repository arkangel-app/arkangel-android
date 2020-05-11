package com.arkangel.arkangel.Clases;

public class User {
    private static final User ourInstance = new User();
    public static  String id_user;
    public static  String token;
    public static User getInstance() {
        return ourInstance;
    }

    private User() {
    }
}
