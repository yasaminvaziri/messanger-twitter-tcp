package shared.events.backsEvent;

import client.graphic.messaging.GroupsView;

public class SendBackToGroupsEvent {
    private GroupsView groupsView;
    private String username;

    public SendBackToGroupsEvent(GroupsView groupsView, String username) {
        this.groupsView = groupsView;
        this.username = username;
    }

    public GroupsView getGroupsView() {
        return groupsView;
    }

    public void setGroupsView(GroupsView groupsView) {
        this.groupsView = groupsView;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
