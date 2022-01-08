package shared.events.homeEvents;

import client.graphic.friendLists.Friends;

public class SendFriendsEvent {
    private Friends friends;

    public SendFriendsEvent(Friends friends) {
        this.friends = friends;
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }
}
