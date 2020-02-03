package com.ioverlap.dojo.chatroom.controller;

import com.ioverlap.dojo.chatroom.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(User user) {
        return "login";
    }
}
