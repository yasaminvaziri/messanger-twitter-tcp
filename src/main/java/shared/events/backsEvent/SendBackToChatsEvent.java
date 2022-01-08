package shared.events.backsEvent;

import client.graphic.messaging.ChatsView;

public class SendBackToChatsEvent {
    private ChatsView chatsView;
    private String username;
    public SendBackToChatsEvent(ChatsView chatsView, String username) {
        this.chatsView = chatsView;
        this.username = username;
    }

    public ChatsView getChatsView() {
        return chatsView;
    }

    public void setChatsView(ChatsView chatsView) {
        this.chatsView = chatsView;
    }
}

