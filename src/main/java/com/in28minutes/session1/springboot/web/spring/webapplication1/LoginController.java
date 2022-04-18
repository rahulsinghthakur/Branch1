package com.in28minutes.session1.springboot.web.spring.webapplication1;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//To create a login controller . At local host "Hello World " is displayed

//@Controller picks up the method as a request mapping
@Controller

public class LoginController {

    @Autowired
    LoginService service1;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage( ModelMap model) {
        return "login";
   }

    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public String showWelcomePage( ModelMap model , @RequestParam String name , @RequestParam String password)
    {

        boolean isValidUser = service1.validateUser(name,password);
        if(!isValidUser) {
            Object errorMessage = model.put("errorMessage", "Invalid Credentials!");
            return "login";
        }
        model.put("name" , name);
        model.put("password" , password);
        return "welcome";
    }

}
