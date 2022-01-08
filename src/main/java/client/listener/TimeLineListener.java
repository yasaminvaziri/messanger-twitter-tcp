package client.listener;

import shared.events.timeLineEvents.*;
import client.controller.MainController;

public class TimeLineListener {
    public void sendTweet(SendTweetEvent e){
        MainController.tweetAgent.addTweet(e);
    }

    public void addTweetToSavedMessages(SavedMessageEvent e){
        MainController.messageAgent.addTweetToSaved(e);
    }

    public void forwardTweet(SendForwardEvent e){
        MainController.messageAgent.forwardTweet(e);
    }

    public void retweet(SendRetweetEvent e){
        MainController.tweetAgent.retweet(e);
    }

    public void reportTweet(SendReportEvent e){
        MainController.tweetAgent.reportTweet(e);
    }

    public void disLike(SendDislikeEvent e){
        MainController.tweetAgent.dislikeTweet(e);
    }

    public void like(SendLikeEvent e){
        MainController.tweetAgent.likeTweet(e);
    }

    public void showProfile(SendShowProfileEvent e){
        MainController.tweetAgent.goToProfile(e);
    }
}
