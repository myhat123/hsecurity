package com.jxgm.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value="/login", method={RequestMethod.POST, RequestMethod.GET})
    public String login() {
        if ((request.getMethod()).equals(RequestMethod.GET)) {
            return "login";
        }

        Subject currentUser = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
        // token.setRememberMe(true);
        currentUser.login(token);
        System.out.println(currentUser.isAuthenticated());

        return "login";
        
    }

}