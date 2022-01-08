package server.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.dataBase.Load;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.models.User;

import java.io.*;
import java.util.LinkedList;

public class Tweet {
     private static final Logger logger = LogManager.getLogger(Tweet.class);

    private long user;
    private String t;
    private LinkedList<Integer> commentList = new LinkedList<>();
    private LinkedList<String> liker = new LinkedList<String>();
    private LinkedList<String>reportedBy = new LinkedList<>();
    private Tweet rTweet;
     public int like = 0;
    private int id;



    public Tweet(User user, String t) {
        this.id = Load.lastIdTweet();
        Load.tweetList.add(this);
        this.user = user.getId();
        this.t = t;
        liker = new LinkedList<>();
        commentList = new LinkedList<>();
        reportedBy = new LinkedList<>();


    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser(){
        return Load.loadUser(user);
    }

    public Tweet getrTweet() {
        return rTweet;
    }

    public void setrTweet(Tweet rTweet) {
        this.rTweet = rTweet;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public LinkedList<Integer> getCommentList(){
        return commentList;
    }

    public void setCommentList(Tweet tweet){
        commentList.add(tweet.getId());
    }

    public void showT(){
        System.out.println(t);
    }

    public LinkedList<String> getLiker() {
        return liker;
    }

    public void setLiker(LinkedList<String> liker) {
        this.liker = liker;
    }

    public LinkedList<String> getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(LinkedList<String> reportedBy) {
        this.reportedBy = reportedBy;
    }

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public  void  saveTweet()  {
        String s = gson.toJson(this);
        String path = "resources/saveTweet";
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
                logger.info("file created for tweet");
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
