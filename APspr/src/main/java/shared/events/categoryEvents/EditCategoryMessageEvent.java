package shared.events.categoryEvents;

import server.model.messaging.Message;

import java.util.LinkedList;

public class EditCategoryMessageEvent {
    private String username;
    private LinkedList<String> members;
    private Message message;
    private String new_message;

    public EditCategoryMessageEvent(String username, LinkedList<String> members, Message message, String new_message) {
        this.username = username;
        this.members = members;
        this.message = message;
        this.new_message = new_message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LinkedList<String> getMembers() {
        return members;
    }

    public void setMembers(LinkedList<String> members) {
        this.members = members;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getNew_message() {
        return new_message;
    }

    public void setNew_message(String new_message) {
        this.new_message = new_message;
    }
}
