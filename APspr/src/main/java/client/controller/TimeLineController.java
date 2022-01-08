package client.controller;

import server.model.Tweet;
import shared.events.timeLineEvents.SendDislikeEvent;
import shared.events.timeLineEvents.SendLikeEvent;
import shared.events.timeLineEvents.SendTweetEvent;
import client.graphic.timeLine.TimeLineView;
import client.stream.StreamUtil;
import shared.GsonUtil;
import shared.networking.Request;
import shared.networking.Response;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class TimeLineController {
   TimeLineView timeLineView;
   public TimeLineController(TimeLineView timeLineView){
       this.timeLineView = timeLineView;
   }
   public void sendTweetPressed(String username, String content, File f){
       try (FileReader reader = new FileReader("src/main/java/client/config/controller.properties")){
           Properties properties = new Properties();
           properties.load(reader);
           SendTweetEvent sendTweetEvent = new SendTweetEvent(MainController.username,content,null);
           StreamUtil.sendRequest(new Request(properties.getProperty("tweet"), GsonUtil.getGson().toJson(sendTweetEvent)));
           Response response = StreamUtil.getResponse();
//           if (!response.status.equals("accepted")){
//               System.out.println("error");
//           }


       }catch (Exception e){
           e.printStackTrace();
       }

   }
   public void sendLikePressed(String username, Tweet tweet){
       SendLikeEvent sendLikeEvent = new SendLikeEvent(MainController.username,tweet);
       StreamUtil.sendRequest(new Request("like",GsonUtil.getGson().toJson(sendLikeEvent)));
       Response response = StreamUtil.getResponse();
       if (!response.status.equals("accepted")){
           StreamUtil.sendRequest(new Request("invalid","denied"));
       }
   }
    public void sendDisLikePressed(String username, Tweet tweet){
        SendDislikeEvent sendDislikeEvent = new SendDislikeEvent(MainController.username,tweet);
        StreamUtil.sendRequest(new Request("dislike",GsonUtil.getGson().toJson(sendDislikeEvent)));
        Response response = StreamUtil.getResponse();
        if (!response.status.equals("accepted")){
            StreamUtil.sendRequest(new Request("invalid","denied"));
        }
    }


}
