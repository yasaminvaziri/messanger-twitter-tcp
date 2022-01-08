package server.app.controller;


import client.controller.MainController;
import shared.events.backsEvent.SendBackToTimeLine;
import shared.events.profileEvent.SeeProfileEvent;
import shared.events.timeLineEvents.*;
import client.graphic.profile.ShowProfileView;
import client.graphic.timeLine.TimeLineView;
import server.controller.FileImage;
import server.model.Tweet;
import server.dataBase.Load;
import server.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.LinkedList;

public class TweetAgent {
    private static final Logger logger = LogManager.getLogger(TweetAgent.class);

    public void addTweet(SendTweetEvent e){
        String username = e.getUsername();
        String t = e.getContent();
        byte[] f = e.getImage();
        User user = Load.loadUser(username);
        Tweet tweet = new Tweet(user,t);
        user.getTweetList().add(tweet.getId());
        user.updateLastSeen();
        if (f != null)

            FileImage.saveTweetPhoto(null, tweet.getId());
        tweet.saveTweet();
        user.saveUser();
          logger.info(user.getUsername() + " added a tweet");
    }
    public void showProfile(SeeProfileEvent e){
        String target = e.getTarget();
        ShowProfileView showProfileView = e.getShowProfileView();
        User user = Load.loadUser(target);
        showProfileView.showProfile(user.getUsername());

    }
    public void backToTimeLine(SendBackToTimeLine e){
        TimeLineView timeLineView = e.getTimeLineView();
        timeLineView.timeLinePage(showTweet(MainController.username));
    }

    public LinkedList<Tweet> showTweet(String username) {
        LinkedList<Integer> followingTweets = new LinkedList<>();
        User user = Load.loadUser(username);
        followingTweets.addAll(user.getTweetList());
        for (String target: user.getFollowings()) {
            User targetUser = Load.loadUser(target);
            if (targetUser != null && !user.getMuted().contains(targetUser.getUsername())) {
                followingTweets.addAll(targetUser.getTweetList());
            }
        }return idToTweet(followingTweets);
    }


    public LinkedList<Tweet> idToTweet(LinkedList<Integer> ar) {
        LinkedList<Tweet> res= new LinkedList<>();
        for (int i : ar)
            res.add(Load.loadTweet(i));
        return res;
    }
    public void addComment(SendCommentEvent e){
        String username = e.getUsername();
        String comment = e.getComment();
        Tweet tweet = e.getTweet();
        User user = Load.loadUser(username);
        Tweet new_tweet = new Tweet(user,comment);
        tweet.getCommentList().add(new_tweet.getId());
        logger.info(user.getUsername() + " " + "added a comment");
        new_tweet.saveTweet();
        user.updateLastSeen();
        user.saveUser();
        tweet.saveTweet();
    }

    public boolean findUser(String username, String target){
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        if (targetUser == null){
            return false;
        }
        else if(user == null || user.getReportedU().contains(targetUser.getUsername())){
            return false;
        }
        return true;
    }

    public void goToProfile(SendShowProfileEvent e){
        ShowProfileView showProfileView = e.getShowProfileView();
        showProfileView.showProfile(e.getTweet().getUser().getUsername());
    }

    public void likeTweet(SendLikeEvent e){
        String username = e.getUsername();
        Tweet tweet = e.getTweet();
        User user = Load.loadUser(username);
        if (!tweet.getLiker().contains(user.getUsername())) {
            tweet.like++;
            user.updateLastSeen();
            tweet.getLiker().add(user.getUsername());
            tweet.saveTweet();
            logger.info(user.getUsername() + " " + "liked tweet");
            tweet.getUser().saveUser();
        }
        else
            return;
    }

    public void dislikeTweet(SendDislikeEvent e){
        String username = e.getUsername();
        Tweet tweet = e.getTweet();
        User user = Load.loadUser(username);
        if (tweet.getLiker().contains(user.getUsername())){
            tweet.like--;
            user.updateLastSeen();
            tweet.getLiker().remove(username);
            tweet.saveTweet();
            logger.info(user.getUsername() + " " + "disliked a tweet");
            tweet.getUser().saveUser();
        }
        else
            return;
    }
    public void reportTweet(SendReportEvent e){
        String username = e.getUsername();
        Tweet tweet = e.getTweet();
        User user = Load.loadUser(username);
        if (!tweet.getReportedBy().contains(user.getUsername())){
            tweet.getReportedBy().add(user.getUsername());
            tweet.saveTweet();
            logger.info(user.getUsername() + " " + "reported a tweet");
            tweet.getUser().saveUser();
            if (tweet.getReportedBy().size() >= 3) {
                tweet.getUser().getTweetList().remove(tweet);
                deleteTweet(tweet);
            }else
                return;
        }
        else
            return;
    }

    public void deleteTweet(Tweet tweet) {
        if (tweet != null) {
            try {
                String path = "resources/saveTweet";
                path += "/" + tweet.getId();
                path += ".txt";
                File file = new File(path);
                if (file.exists()) {
                    System.out.println("1");
                }
                if (file.delete()) {
                    System.out.println(file.getName() + "deleted");
                    logger.info(tweet.getT() + " deleted file ");
                    System.exit(0);

                } else {
                    System.out.println("failed");
                     logger.error(" could not delete file");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("tweet does not exist");
        }
    }

    public Tweet retweet(SendRetweetEvent e){
        String username = e.getUsername();
        Tweet tweet = e.getTweet();
        User user = Load.loadUser(username);
        Tweet retweeted_tweet = new Tweet(user,tweet.getT());
        user.getTweetList().add(retweeted_tweet.getId());
        retweeted_tweet.setrTweet(tweet);
        retweeted_tweet.saveTweet();
        user.updateLastSeen();
        user.saveUser();
        logger.info(user.getUsername() + " " + "retweeted a tweet");
        return tweet;
    }

    public LinkedList<Tweet> discover(String username){
        LinkedList<Integer> allTweets = new LinkedList<>();
        User user = Load.loadUser(username);
        for (int i = 0; i < Load.tweetList.size(); i++) {
            if (user.getMuted().contains(Load.tweetList.get(i).getUser().getUsername()) ||
                    user.getBlockList().contains(Load.tweetList.get(i).getUser().getUsername()) ||
                    user.getReportedU().contains(Load.tweetList.get(i).getUser().getUsername())
                    || !Load.tweetList.get(i).getUser().isPublic())
                continue;
            allTweets.add(Load.tweetList.get(i).getId());
        }
        return idToTweet(allTweets);
    }

}
