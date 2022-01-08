package shared.events.categoryEvents;

import server.model.messaging.Message;

import java.util.LinkedList;

public class DeleteCategoryMessageEvent {
    private String username;
    private LinkedList<String> members;
    private Message message;

    public DeleteCategoryMessageEvent(String username, LinkedList<String> members, Message message) {
        this.username = username;
        this.members = members;
        this.message = message;
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
}
