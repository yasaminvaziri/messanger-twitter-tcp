package shared.events.messageEvents;

import server.model.messaging.Message;

import java.util.LinkedList;

public class EditGroupMessageEvent {
    private String username, new_message;
    private Message message;
    private LinkedList<String> members;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNew_message() {
        return new_message;
    }

    public void setNew_message(String new_message) {
        this.new_message = new_message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public LinkedList<String> getMembers() {
        return members;
    }

    public void setMembers(LinkedList<String> members) {
        this.members = members;
    }

    public EditGroupMessageEvent(String username, String new_message, Message message, LinkedList<String> members) {
        this.username = username;
        this.new_message = new_message;
        this.message = message;
        this.members = members;

    }
}
