package client.listener;

import shared.events.backsEvent.SendBackToTimeLine;
import shared.events.timeLineEvents.SendCommentEvent;
import client.controller.MainController;

public class CommentListener {
    public void sendComment(SendCommentEvent e){
        MainController.tweetAgent.addComment(e);
    }
    public void backToTimeLine(SendBackToTimeLine e){
        MainController.tweetAgent.backToTimeLine(e);
    }

}
