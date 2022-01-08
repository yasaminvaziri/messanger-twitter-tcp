package shared.events.categoryEvents;

import client.graphic.friendLists.category.MessageToCategoryView;
import server.model.Group;
import server.model.messaging.Message;

import java.util.LinkedList;

public class SendCategoryMessageEvent {
    private MessageToCategoryView messageToCategoryView;
    private LinkedList<Message> messages;
    private LinkedList<String> members;
    private Group group;

    public SendCategoryMessageEvent(MessageToCategoryView messageToCategoryView, LinkedList<String> members, Group group) {
        this.messageToCategoryView = messageToCategoryView;
        this.members = members;
        this.group = group;
    }

    public MessageToCategoryView getMessageToCategoryView() {
        return messageToCategoryView;
    }

    public void setMessageToCategoryView(MessageToCategoryView messageToCategoryView) {
        this.messageToCategoryView = messageToCategoryView;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }

    public LinkedList<String> getMembers() {
        return members;
    }

    public void setMembers(LinkedList<String> members) {
        this.members = members;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
