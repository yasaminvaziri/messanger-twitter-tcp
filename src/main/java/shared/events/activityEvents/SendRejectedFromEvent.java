package shared.events.activityEvents;

import client.graphic.activity.RejectedFromView;

public class SendRejectedFromEvent {
    private RejectedFromView rejectedFromView;

    public SendRejectedFromEvent(RejectedFromView rejectedFromView) {
        this.rejectedFromView = rejectedFromView;
    }

    public RejectedFromView getRejectedFromView() {
        return rejectedFromView;
    }

    public void setRejectedFromView(RejectedFromView rejectedFromView) {
        this.rejectedFromView = rejectedFromView;
    }
}
