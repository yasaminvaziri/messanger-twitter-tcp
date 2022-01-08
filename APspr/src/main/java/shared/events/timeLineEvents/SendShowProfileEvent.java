package shared.events.timeLineEvents;

import client.graphic.profile.ShowProfileView;
import server.model.Tweet;

public class SendShowProfileEvent {
    private ShowProfileView showProfileView;
    private Tweet tweet;

    public SendShowProfileEvent(ShowProfileView showProfileView, Tweet tweet) {
        this.showProfileView = showProfileView;
        this.tweet = tweet;
    }

    public ShowProfileView getShowProfileView() {
        return showProfileView;
    }

    public void setShowProfileView(ShowProfileView showProfileView) {
        this.showProfileView = showProfileView;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
