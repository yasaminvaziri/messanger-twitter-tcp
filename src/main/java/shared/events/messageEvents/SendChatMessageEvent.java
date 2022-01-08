package shared.events.messageEvents;

import java.io.File;

public class SendChatMessageEvent {
    private String username, target, messageText;
    private File f;

    public SendChatMessageEvent(String username, String target, String messageText, File f) {
        this.username = username;
        this.target = target;
        this.messageText = messageText;
        this.f = f;
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

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }
}
