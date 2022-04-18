package com.in28minutes.session1.springboot.web.spring.webapplication1.sevice;

public class LoginService {
    public boolean validateUser(String userid, String password) {
        return userid.equalsIgnoreCase("geetha") && password.equalsIgnoreCase("test");

    }

}