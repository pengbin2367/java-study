package com.atqingke.springframework.test.bean;

public class UserService {

    private String name;

    public UserService() {}

    public UserService(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }

    public void queryUserInfo() {
        System.out.println("Searching UserInfo: " + name);
    }
}
