package shared.events.backsEvent;

import client.graphic.friendLists.Friends;

public class SendBackToFriendsEvent {
    private Friends friends;
    private String username;

    public SendBackToFriendsEvent(Friends friends, String username) {
        this.friends = friends;
        this.username = username;
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

