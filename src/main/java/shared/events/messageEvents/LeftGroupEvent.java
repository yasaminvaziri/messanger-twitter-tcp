package shared.events.messageEvents;

import server.model.Groups;

public class LeftGroupEvent {
    private Groups groups;
    private String username;

    public LeftGroupEvent(Groups groups, String username) {
        this.groups = groups;
        this.username = username;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
