package shared.events.loginEvents;

public class SendLoginEvent {
    private String username, password, warnLabel;


    public SendLoginEvent(String username, String password){
        this.username = username;
        this.password = password;
        ///this.warnLabel = warnLabel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWarnLabel() {
        return warnLabel;
    }

    public void setWarnLabel(String warnLabel) {
        this.warnLabel = warnLabel;
    }
}
