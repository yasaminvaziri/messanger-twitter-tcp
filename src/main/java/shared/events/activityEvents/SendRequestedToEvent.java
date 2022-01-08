package shared.events.activityEvents;

import client.graphic.activity.RequestedToView;

public class SendRequestedToEvent {
    private RequestedToView requestedToView;

    public SendRequestedToEvent(RequestedToView requestedToView) {
        this.requestedToView = requestedToView;
    }

    public RequestedToView getRequestedToView() {
        return requestedToView;
    }

    public void setRequestedToView(RequestedToView requestedToView) {
        this.requestedToView = requestedToView;
    }
}
