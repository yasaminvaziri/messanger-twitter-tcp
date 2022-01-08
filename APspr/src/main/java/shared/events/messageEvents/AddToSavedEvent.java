package shared.events.messageEvents;

import java.io.File;

public class AddToSavedEvent {
   private String content, username;
   private File f;

    public AddToSavedEvent(String content, String username, File f) {
        this.content = content;
        this.username = username;
        this.f = f;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }
}
