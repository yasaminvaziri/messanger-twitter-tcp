package shared.events.activityEvents;

public class SendRejectSilentEvent {
    private String username, target, warn;

    public SendRejectSilentEvent(String username, String target, String warn) {
        this.username = username;
        this.target = target;
        this.warn = warn;
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

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }
}
