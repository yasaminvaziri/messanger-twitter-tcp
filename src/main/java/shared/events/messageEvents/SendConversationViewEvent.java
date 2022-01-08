package shared.events.messageEvents;

import client.graphic.messaging.ConversationView;
import server.model.messaging.Message;

import java.util.LinkedList;

public class SendConversationViewEvent {
    private ConversationView conversationView;
    private String username, target;
    private LinkedList<Message> messages;

    public SendConversationViewEvent(ConversationView conversationView, String username, String target) {
        this.conversationView = conversationView;
        this.username = username;
        this.target = target;
    }

    public ConversationView getConversationView() {
        return conversationView;
    }

    public void setConversationView(ConversationView conversationView) {
        this.conversationView = conversationView;
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

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }
}
