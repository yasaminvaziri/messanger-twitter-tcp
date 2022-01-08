package shared.events.activityEvents;

import client.graphic.activity.UnfollowedFrom;

public class SendUnfollowedFromEvent {
    private UnfollowedFrom unfollowedFrom;

    public SendUnfollowedFromEvent(UnfollowedFrom unfollowedFrom) {
        this.unfollowedFrom = unfollowedFrom;
    }

    public UnfollowedFrom getUnfollowedFrom() {
        return unfollowedFrom;
    }

    public void setUnfollowedFrom(UnfollowedFrom unfollowedFrom) {
        this.unfollowedFrom = unfollowedFrom;
    }
}
