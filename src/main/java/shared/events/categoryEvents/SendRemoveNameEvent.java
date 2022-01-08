package shared.events.categoryEvents;

import server.model.Group;

public class SendRemoveNameEvent {
    private String username, name;
    private Group group;

    public SendRemoveNameEvent(String username, String name, Group group) {
        this.username = username;
        this.name = name;
        this.group = group;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
