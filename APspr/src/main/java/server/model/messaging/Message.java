package server.model.messaging;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.dataBase.Load;
import server.model.Tweet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Message {
    private static final Logger logger = LogManager.getLogger(Message.class);
  private String sender;
  private String message;
  private  Tweet tweet;
   private int id;
    boolean seen;

    public Message(String sender, String message){
        this.id = Load.lastIdMessage();
        this.sender = sender;
        this.message = message;
        this.tweet = null;
        seen = false;

    }

    public Message(String sender, Tweet tweet) {
        this.sender = sender;
        this.tweet = tweet;
        this.message = null;
        seen = false;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String showM(String username){
        if (!sender.equals(username)){
            seen = true;
        }
        if (tweet == null){
            return message;
        }
        else
            return tweet.getT();
        }

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public  void  saveMessage()  {
        String s = gson.toJson(this);
        String path = "resources/saveMessage";
        File tmp = new File(path);
        path +="/" + id;
        path += ".txt";
        File file = new File(path);
        try {
            if (!file.exists())
                file.createNewFile();
            else {
                file.delete();
                file.createNewFile();
                logger.info("file created for message");
            }
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            gson.toJson(this,printStream);
            printStream.close();
        }catch (IOException e){
            System.out.println("error");
            logger.fatal("could not create file");
        }

    }


}
