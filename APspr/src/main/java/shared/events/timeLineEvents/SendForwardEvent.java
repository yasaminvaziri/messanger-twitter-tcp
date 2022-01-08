package shared.events.timeLineEvents;

import server.model.Tweet;

public class SendForwardEvent {
    private String username, target;
    private Tweet tweet;

    public SendForwardEvent(String username, String target, Tweet tweet) {
        this.username = username;
        this.target = target;
        this.tweet = tweet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
