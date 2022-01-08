package shared.events.messageEvents;

import client.graphic.messaging.GroupConversationView;
import server.model.Groups;
import server.model.messaging.Message;

import java.util.LinkedList;

public class SendGroupChatViewEvent {
    private GroupConversationView groupConversationView;
    private LinkedList<String> members;
    private LinkedList<Message> messages;
    private Groups groups;
    private String username;

    public SendGroupChatViewEvent(GroupConversationView groupConversationView,Groups groups, LinkedList<String> members) {
        this.groupConversationView = groupConversationView;
        this.groups = groups;
        this.members = members;
    }

    public GroupConversationView getGroupConversationView() {
        return groupConversationView;
    }

    public void setGroupConversationView(GroupConversationView groupConversationView) {
        this.groupConversationView = groupConversationView;
    }

    public LinkedList<String> getMembers() {
        return members;
    }

    public void setMembers(LinkedList<String> members) {
        this.members = members;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
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
