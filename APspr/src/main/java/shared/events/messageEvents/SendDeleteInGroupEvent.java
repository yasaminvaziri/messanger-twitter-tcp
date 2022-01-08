package shared.events.messageEvents;

import server.model.messaging.Message;

import java.util.LinkedList;

public class SendDeleteInGroupEvent {
    private String username;
    private Message message;
    private LinkedList<String> members;

    public SendDeleteInGroupEvent(String username, Message message, LinkedList<String> members) {
        this.username = username;
        this.message = message;
        this.members = members;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
