package com.ioverlap.dojo.chatroom.controller;

import com.ioverlap.dojo.chatroom.configuration.MessageDecoder;
import com.ioverlap.dojo.chatroom.configuration.MessageEncoder;
import com.ioverlap.dojo.chatroom.model.Message;
import com.ioverlap.dojo.chatroom.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = WebSocketController.PREFIX + "/{username}",
        encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class WebSocketController {

    public static final String PREFIX = "/chat";

    private static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(Message message) throws IOException, EncodeException {
        for(String username: onlineSessions.keySet()) {
            sendMessageTo(username, message);
        }
    }

    private static void sendMessageTo(String recipient, Message message) throws IOException, EncodeException {
        Session session = onlineSessions.get(recipient);
        if(session.isOpen()) {
            session.getBasicRemote().sendObject(message);
            logger.info("[Message] Sent message from: " + message.getSender() + "  to: " + recipient);
            logger.info("[Message]" + message.toString());
        }
    }

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        String username = session.getPathParameters().get("username");
        onlineSessions.put(username, session);
        logger.info("[Connected] " + username);
        Message message = new Message("SERVER","ALL",username + " has connected.", onlineSessions.keySet(), MessageType.ENTER);
        sendMessageToAll(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        String username = session.getPathParameters().get("username");
        onlineSessions.remove(username);
        logger.info("[Disconnect] " + username);
        Message message = new Message("SERVER","ALL",username + " has disconnected.", onlineSessions.keySet(), MessageType.LEAVE);
        sendMessageToAll(message);
    }

    @OnMessage
    public void OnMessage(Session session, Message message) throws IOException, EncodeException {
        String username = session.getPathParameters().get("username");
        logger.info("[Message] Received message from: " + username + " to: " + message.getRecipient());
        logger.info("[Message] " + message.toString());
        message.setType(MessageType.CHAT);
        if(message.getRecipient().equals("ALL")) {
            sendMessageToAll(message);
        }
        else {
            sendMessageTo(message.getRecipient(), message);
        }
    }
}
