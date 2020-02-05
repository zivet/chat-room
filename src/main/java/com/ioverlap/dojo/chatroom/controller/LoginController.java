package com.ioverlap.dojo.chatroom.controller;

import com.ioverlap.dojo.chatroom.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(User user) {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "login";
        }
        model.addAttribute("webSocketUrl", "ws://localhost:8080/chat/");
        return "chat";
    }

}
