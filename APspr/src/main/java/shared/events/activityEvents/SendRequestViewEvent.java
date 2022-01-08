package shared.events.activityEvents;

import client.graphic.activity.RequestsView;

public class SendRequestViewEvent {
    private RequestsView requestsView;

    public SendRequestViewEvent(RequestsView requestsView) {
        this.requestsView = requestsView;
    }

    public RequestsView getRequestsView() {
        return requestsView;
    }

    public void setRequestsView(RequestsView requestsView) {
        this.requestsView = requestsView;
    }
}
