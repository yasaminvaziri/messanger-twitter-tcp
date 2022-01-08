package shared.events.settingsEvent;

public class SendDeleteAccEvent {
    private String username;

    public SendDeleteAccEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
