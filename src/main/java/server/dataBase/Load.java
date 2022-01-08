package server.dataBase;

import com.google.gson.*;
import server.model.Tweet;
import server.models.User;
import server.model.messaging.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;



public class Load {

   public static LinkedList<User>userList = new LinkedList<>();

    public static boolean UserExists(String username) {
        for (User user: userList) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }
  static public void loadUser(){
      File file = new File("resources/saveUser");
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
      Gson gson = gsonBuilder.setPrettyPrinting().create();
        File[] f = file.listFiles();
        for (File g:f){
            try {
                FileReader fileReader = new FileReader(g.getPath());
                User user = gson.fromJson(fileReader,User.class);
                fileReader.close();
                userList.add(user);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

     public static User loadUser(long id){
      for (User user : userList){
          if (user.getId() == id){
              return user;
          }
      }
      return null;
     }

     public static User loadUser(String username){
      for (User user : userList){
          if (user.getUsername().equals(username)){
              return user;
          }
      }
      return null;
     }
    public static Tweet loadTweet(int id) {
      for (Tweet tweet : tweetList)
          if (id == tweet.getId())
              return tweet;
      return null;
    }

    public static long lastIdUser(){
      long max = 0;
      for (User user : userList){
          max = (long) Math.max(max, user.getId());
      }
      return max;
    }

    public static LinkedList<Message>messages = new LinkedList<>();
 static public void loadMessage(){
        File file = new File("resources/saveMessage");
        Gson gson = new Gson();
        File[] f = file.listFiles();
        for (File g:f){
            try {
                FileReader fileReader = new FileReader(g.getPath());
                Message message = gson.fromJson(fileReader,Message.class);
                fileReader.close();
                messages.add(message);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static int lastIdMessage(){
     int max = 0;
     for (Message message : messages){
         max = Math.max(max,message.getId());
     }
     return max + 1;
    }

    public static LinkedList<Tweet>tweetList = new LinkedList<>();
    static public void loadTweet(){
        File file = new File("resources/saveTweet");
        Gson gson = new Gson();
        File[] f = file.listFiles();
        for (File g:f){
            try {
                FileReader fileReader = new FileReader(g.getPath());
                Tweet tweet = gson.fromJson(fileReader,Tweet.class);
                fileReader.close();
                tweetList.add(tweet);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static int lastIdTweet(){
        int max = 0;
        for (Tweet tweet : tweetList){
            max = Math.max(max,tweet.getId());
        }
        return max + 1;
    }



}
class LocalDateTimeDeserializer implements JsonDeserializer< LocalDateTime > {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(),
                DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss").withLocale(Locale.ENGLISH));
    }
}
