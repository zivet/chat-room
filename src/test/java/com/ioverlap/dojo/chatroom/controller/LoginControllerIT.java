package com.ioverlap.dojo.chatroom.controller;

import com.ioverlap.dojo.chatroom.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginControllerInjectionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors());
    }

    @Test
    public void testLoginInputLessLength() throws Exception {
        String username = "kk";
        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", username))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void testLoginInputGreaterLength() throws Exception {
        String username = "supercalifragilisticexpialidocious";
        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", username))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void testLoginInputEmpty() throws Exception {
        String username = "";
        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", username))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void testLogin() throws Exception {
        String username = "pepe";
        mockMvc.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", username))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.view().name("chat"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.model().attribute("user", new User(username)));
    }
}