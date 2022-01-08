package shared.events.messageEvents;

import client.graphic.messaging.SavedMessagesView;

public class SendSavedMessageViewEvent {
    private SavedMessagesView savedMessagesView;

    public SendSavedMessageViewEvent(SavedMessagesView savedMessagesView) {
        this.savedMessagesView = savedMessagesView;
    }

    public SavedMessagesView getSavedMessagesView() {
        return savedMessagesView;
    }

    public void setSavedMessagesView(SavedMessagesView savedMessagesView) {
        this.savedMessagesView = savedMessagesView;
    }
}
