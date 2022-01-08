package shared.events.messageEvents;

import server.model.messaging.Message;

public class DeleteSavedMessageEvent {
    private Message message;
    private String username;

    public DeleteSavedMessageEvent(Message message, String username) {
        this.message = message;
        this.username = username;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
