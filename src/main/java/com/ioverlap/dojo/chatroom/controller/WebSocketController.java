package com.ioverlap.dojo.chatroom.controller;

import com.ioverlap.dojo.chatroom.configuration.MessageDecoder;
import com.ioverlap.dojo.chatroom.configuration.MessageEncoder;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(value = WebSocketController.PREFIX + "{username}",
        encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class WebSocketController {

    public static final String PREFIX = "/chat";

    @OnOpen
    public void onOpen() {

    }

    @OnClose
    public void onClose() {

    }

    @OnMessage
    public void OnMessage() {

    }
}
