package shared.events.messageEvents;

import client.graphic.messaging.ChatsView;

public class SendChatViewEvent {
    private ChatsView chatsView;

    public SendChatViewEvent(ChatsView chatsView) {
        this.chatsView = chatsView;
    }

    public ChatsView getChatsView() {
        return chatsView;
    }

    public void setChatsView(ChatsView chatsView) {
        this.chatsView = chatsView;
    }
}
