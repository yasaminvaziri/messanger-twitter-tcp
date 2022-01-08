package shared.events.categoryEvents;

import java.io.File;
import java.util.LinkedList;

public class SendMessageToCategoryEvent {
    private String username, messageText;
    private LinkedList<String> members;
    private File f;

    public SendMessageToCategoryEvent(String username, String messageText, LinkedList<String> members, File f) {
        this.username = username;
        this.messageText = messageText;
        this.members = members;
        this.f = f;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LinkedList<String> getMembers() {
        return members;
    }

    public void setMembers(LinkedList<String> members) {
        this.members = members;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }
}
