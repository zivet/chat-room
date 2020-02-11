package com.ioverlap.dojo.chatroom.model;

import java.util.Set;

public class Message {

    private String sender;

    private String recipient;

    private String body;

    private Set<String> onlineUsers;

    private MessageType type;

    public Message() {
    }

    public Message(String sender, String recipient, String body, Set<String> onlineUsers, MessageType type) {
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
        this.onlineUsers = onlineUsers;
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<String> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(Set<String> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", body='" + body + '\'' +
                ", onlineUsers=" + onlineUsers +
                ", type=" + type +
                '}';
    }
}
