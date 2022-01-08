package shared.events.backsEvent;

import client.graphic.friendLists.Followings;

public class SendBackToFollowingsEvent {
    private Followings followings;

    public SendBackToFollowingsEvent(Followings followings) {
        this.followings = followings;
    }

    public Followings getFollowings() {
        return followings;
    }

    public void setFollowings(Followings followings) {
        this.followings = followings;
    }
}
