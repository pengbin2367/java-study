package com.atqingke.springframework.test.bean;

public class UserService {

    private String id;

    private String name;

    private UserDao userDao;

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }

    public void queryUserInfo() {
        System.out.println("Searching UserInfo: " + userDao.queryUserName(id));
    }
}
