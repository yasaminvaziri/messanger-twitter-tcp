package server.model;

import server.model.messaging.Message;

import java.util.LinkedList;

public class Groups {
    private LinkedList<String> usernames;
    private String name;
    private LinkedList<Message>messages;


    public Groups(String name){
        this.name = name;
        usernames = new LinkedList<>();
        messages = new LinkedList<>();
    }

    public LinkedList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(LinkedList<String> usernames) {
        this.usernames = usernames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }
}
