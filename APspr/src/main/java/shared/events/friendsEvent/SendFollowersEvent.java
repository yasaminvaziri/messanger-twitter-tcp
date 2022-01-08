package shared.events.friendsEvent;

import client.graphic.friendLists.Followers;

public class SendFollowersEvent {
    private Followers followers;

    public SendFollowersEvent(Followers followers) {
        this.followers = followers;
    }

    public Followers getFollowers() {
        return followers;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }
}
