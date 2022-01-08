package shared.events.timeLineEvents;

import server.model.Tweet;

public class SavedMessageEvent {
    private String sender;
    private Tweet tweet;

    public SavedMessageEvent(String sender, Tweet tweet) {
        this.sender = sender;
        this.tweet = tweet;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
