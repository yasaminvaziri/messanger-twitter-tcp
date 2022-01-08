package shared.events.backsEvent;

import client.graphic.messaging.MessagingView;

public class SendBackToMessagingEvent {
    private MessagingView messagingView;

    public SendBackToMessagingEvent(MessagingView messagingView) {
        this.messagingView = messagingView;
    }

    public MessagingView getMessagingView() {
        return messagingView;
    }

    public void setMessagingView(MessagingView messagingView) {
        this.messagingView = messagingView;
    }
}
