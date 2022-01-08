package shared.events.friendsEvent;

import client.graphic.friendLists.Followings;

public class SendFollowingsEvent {
    private Followings followings;

    public SendFollowingsEvent(Followings followings) {
        this.followings = followings;
    }

    public Followings getFollowings() {
        return followings;
    }

    public void setFollowings(Followings followings) {
        this.followings = followings;
    }
}
