package shared.events.timeLineEvents;

import server.model.Tweet;

public class SendReportEvent {
    private String username;
    private Tweet tweet;

    public SendReportEvent(String username, Tweet tweet) {
        this.username = username;
        this.tweet = tweet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
