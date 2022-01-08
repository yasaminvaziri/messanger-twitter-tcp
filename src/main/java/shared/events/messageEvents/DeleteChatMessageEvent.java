package shared.events.messageEvents;

import server.model.messaging.Message;

public class DeleteChatMessageEvent {
    private String target, username;
    private Message message;

    public DeleteChatMessageEvent(String target, String username, Message message) {
        this.target = target;
        this.username = username;
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
}
