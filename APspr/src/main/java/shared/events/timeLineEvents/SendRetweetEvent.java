package shared.events.timeLineEvents;

import server.model.Tweet;

public class SendRetweetEvent {
    private Tweet tweet;
    private String username;

    public SendRetweetEvent(Tweet tweet, String username) {
        this.tweet = tweet;
        this.username = username;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
