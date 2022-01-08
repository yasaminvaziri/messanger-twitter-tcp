package shared.events.messageEvents;

import server.model.messaging.Message;

public class EditSavedMessageEvent {
    private String text;
    private Message message;

    public EditSavedMessageEvent(String text, Message message) {
        this.text = text;
        this.message = message;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
