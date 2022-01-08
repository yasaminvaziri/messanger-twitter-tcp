package server.app;

import server.controller.FileImage;
import server.model.Tweet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.dataBase.Load;
import server.models.User;
import server.stream.StreamUtil;
import shared.GsonUtil;
import shared.events.timeLineEvents.SendDislikeEvent;
import shared.events.timeLineEvents.SendLikeEvent;
import shared.events.timeLineEvents.SendTweetEvent;
import shared.networking.Request;
import shared.networking.Response;

import java.io.DataOutputStream;
import java.io.IOException;


public class Tweets {
    private static final Logger logger = LogManager.getLogger(Tweets.class);
    public void sendTweet(Request request, DataOutputStream dataOutputStream){
        SendTweetEvent sendTweetEvent = GsonUtil.getGson().fromJson(request.data, SendTweetEvent.class);
        //String username = e.getUsername();
        //String t = e.getContent();
       // File f = e.getImage();
        User user = Load.loadUser(sendTweetEvent.getUsername());
        Tweet tweet = new Tweet(user,sendTweetEvent.getContent());
        user.getTweetList().add(tweet.getId());
        user.updateLastSeen();
        if (sendTweetEvent.getImage() != null) {
            FileImage.saveTweetPhoto(null, tweet.getId());
        }
        try {
            StreamUtil.sendResponse(new Response("","created"),dataOutputStream);

        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        tweet.saveTweet();
        user.saveUser();
        logger.info(user.getUsername() + " added a tweet");
    }

    public void likeTweet(Request request, DataOutputStream dataOutputStream){
        SendLikeEvent sendLikeEvent = GsonUtil.getGson().fromJson(request.data, SendLikeEvent.class);
        //String username = e.getUsername();
       // Tweet tweet = e.getTweet();
        User user = Load.loadUser(sendLikeEvent.getUsername());
        if (!sendLikeEvent.getTweet().getLiker().contains(user.getUsername())) {
            sendLikeEvent.getTweet().like++;
            user.updateLastSeen();
            sendLikeEvent.getTweet().getLiker().add(user.getUsername());
            try {
                StreamUtil.sendResponse(new Response("","created"),dataOutputStream);

            }catch (IOException ioException){
                ioException.printStackTrace();
            }
            sendLikeEvent.getTweet().saveTweet();
            sendLikeEvent.getTweet().getUser().saveUser();
            logger.info(user.getUsername() + " " + "liked tweet");

        }
        else
            return;
    }

    public void dislikeTweet(Request request, DataOutputStream dataOutputStream){
        SendDislikeEvent sendDislikeEvent = GsonUtil.getGson().fromJson(request.data, SendDislikeEvent.class);
        //String username = e.getUsername();
        //Tweet tweet = e.getTweet();
        User user = Load.loadUser(sendDislikeEvent.getUsername());
        if (sendDislikeEvent.getTweet().getLiker().contains(user.getUsername())){
            sendDislikeEvent.getTweet().like--;
            user.updateLastSeen();
            sendDislikeEvent.getTweet().getLiker().remove(sendDislikeEvent.getUsername());
            try {
                StreamUtil.sendResponse(new Response("","created"),dataOutputStream);

            }catch (IOException ioException){
                ioException.printStackTrace();
            }
            sendDislikeEvent.getTweet().saveTweet();
            logger.info(user.getUsername() + " " + "disliked a tweet");
            sendDislikeEvent.getTweet().getUser().saveUser();
        }
        else
            return;
    }
}

