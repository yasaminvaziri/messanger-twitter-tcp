package shared.events.timeLineEvents;

import server.model.Tweet;

public class SendCommentEvent {
    private String username, comment;
    private Tweet tweet;

    public SendCommentEvent(String username, String comment, Tweet tweet){
        this.username = username;
        this.comment = comment;
        this.tweet = tweet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
