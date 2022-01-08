package shared.events.messageEvents;

import server.model.Groups;

public class SendAddGroupMemberEvent {
    private String username, target;
    private Groups groups;

    public SendAddGroupMemberEvent(String username, String target, Groups groups) {
        this.username = username;
        this.target = target;
        this.groups = groups;
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

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}

