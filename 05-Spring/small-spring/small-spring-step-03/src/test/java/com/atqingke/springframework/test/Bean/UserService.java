package com.atqingke.springframework.test.Bean;

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
