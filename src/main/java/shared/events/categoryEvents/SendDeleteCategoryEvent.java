package shared.events.categoryEvents;

import server.model.Group;

public class SendDeleteCategoryEvent {
    private String username;
    private Group name;

    public SendDeleteCategoryEvent(String username, Group name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Group getName() {
        return name;
    }

    public void setName(Group name) {
        this.name = name;
    }
}
