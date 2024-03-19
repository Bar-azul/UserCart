package com.example.ex2app;

import java.util.ArrayList;
import java.util.List;

public class UserListManager {
    private static UserListManager instance;
    private List<User> userList;

    private UserListManager() {
        userList = new ArrayList<>();
    }

    public static synchronized UserListManager getInstance() {
        if (instance == null) {
            instance = new UserListManager();
        }
        return instance;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public boolean isUsernameExists(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getUserList() {
        return userList;
    }
}
