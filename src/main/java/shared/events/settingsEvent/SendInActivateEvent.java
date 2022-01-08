package shared.events.settingsEvent;

public class SendInActivateEvent {
    private String username;

    public SendInActivateEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
