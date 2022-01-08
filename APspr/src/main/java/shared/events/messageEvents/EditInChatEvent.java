package shared.events.messageEvents;

import server.model.messaging.Message;

public class EditInChatEvent {
    private String username, target, new_message;
    private Message message;

    public EditInChatEvent(String username, String target, String new_message, Message message) {
        this.username = username;
        this.target = target;
        this.new_message = new_message;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
}
