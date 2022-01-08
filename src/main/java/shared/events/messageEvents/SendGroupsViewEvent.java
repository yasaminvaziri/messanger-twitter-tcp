package shared.events.messageEvents;

import client.graphic.messaging.GroupsView;

public class SendGroupsViewEvent {
    private GroupsView groupsView;

    public SendGroupsViewEvent(GroupsView groupsView) {
        this.groupsView = groupsView;
    }

    public GroupsView getGroupsView() {
        return groupsView;
    }

    public void setGroupsView(GroupsView groupsView) {
        this.groupsView = groupsView;
    }
}
