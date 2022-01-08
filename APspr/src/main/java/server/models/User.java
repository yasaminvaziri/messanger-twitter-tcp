package server.models;

import com.google.gson.*;
import server.dataBase.Load;
import server.model.Group;
import server.model.Groups;
import server.model.messaging.Chat;
import server.model.messaging.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class User {
    private static final Logger logger = LogManager.getLogger(User.class);

   private LinkedList<String>followers;
   private LinkedList<String>followings;
   private LinkedList<String>blockList;
   private LinkedList<String>rejectedFrom;
   private LinkedList<Integer> tweetList = new LinkedList<>();
   private LinkedList<String>requests = new LinkedList<>();
   private LinkedList<String>haveSentRequest;
   private LinkedList<String>muted;
   private LinkedList<String>reportedU;
   private LinkedList<Chat>chats;
   private LinkedList<Message>savedMessages;
   private LinkedList<Group>groups;
   private LinkedList<String>unfollowed_from;
   private LinkedList<Groups>groupsName;
   private String username;
   private String password;
   private String fullName;
   private String email;
   private String birthDay;
   private String phoneNumber;
   private String biography;
   private long id;
   private boolean Public;
   private boolean isActive;
   private boolean permit;
   private boolean isOnline;
   private int showLastSeen;
   private LocalDateTime lastTime;


 public User( String username,String email, String password,String fullName,String birthDay
              ,String phoneNumber, String biography){
     Load.userList.add(this);
     this.id = Load.lastIdUser() + 1;
     this.username = username;
     this.password = password;
     this.email = email;
     this.fullName = fullName;
     this.birthDay = birthDay;
     this.phoneNumber = phoneNumber;
     this.biography = biography;
     followers = new LinkedList<>();
     followings = new LinkedList<>();
     blockList = new LinkedList<>();
     muted = new LinkedList<>();
     rejectedFrom = new LinkedList<>();
     reportedU = new LinkedList<>();
     chats = new LinkedList<>();
     savedMessages = new LinkedList<>();
     groups = new LinkedList<>();
     haveSentRequest = new LinkedList<>();
     unfollowed_from = new LinkedList<>();
     groupsName = new LinkedList<>();
     showLastSeen = 2;
     isActive = true;
     Public = true;
     isOnline = true;
     permit = true;

}
    public String getFullName() {
            return fullName;
    }

    public void setFullName(String fullName) {
            this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public long getId() { return id;}

    public LinkedList<String> getRejectedFrom() {
        return rejectedFrom;
    }

    public LinkedList<String> getRequests() {
        return requests;
    }

    public LinkedList<String> getHaveSentRequest() {
        return haveSentRequest;
    }

    public LinkedList<String> getFollowers() {
        return followers;
    }

    public LinkedList<String> getFollowings() {
        return followings;
    }

    public void setFollowings(LinkedList<String> followings) {
        this.followings = followings;
    }

    public LinkedList<String> getBlockList() {
        return blockList;
    }

    public void setBlockList(LinkedList<String> blockList) {
        this.blockList = blockList;
    }

    public LinkedList<Integer> getTweetList() {
        return tweetList;
    }

    public LinkedList<String> getMuted() {
        return muted;
    }

    public LinkedList<String> getReportedU() {
        return reportedU;
    }

    public LinkedList<Chat> getChats() {
        return chats;
    }

    public void setChats(LinkedList<Chat> chats) {
        this.chats = chats;
    }

    public LinkedList<Message> getSavedMessages() {
        return savedMessages;
    }

    public void setSavedMessages(LinkedList<Message> savedMessages) {
        this.savedMessages = savedMessages;
    }

    public LinkedList<Group> getGroups() {
        return groups;
    }

    public void setGroups(LinkedList<Group> groups) {
        this.groups = groups;
    }

    public boolean isPublic() {
        return Public;
    }

    public void setPublic(boolean aPublic) {
        Public = aPublic;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getShowLastSeen() {
        return showLastSeen;
    }

    public void setShowLastSeen(int showLastSeen) {
        this.showLastSeen = showLastSeen;
    }

    public boolean isPermit() {
        return permit;
    }

    public void setPermit(boolean permit) {
        this.permit = permit;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void updateLastTime(){
     lastTime = LocalDateTime.now();
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    public LinkedList<String> getUnfollowed_from() { return unfollowed_from; }

    public LinkedList<Groups> getGroupsName() {
        return groupsName;
    }

    public void setGroupsName(LinkedList<Groups> groupsName) {
        this.groupsName = groupsName;
    }

    public void updateLastSeen() {
        lastTime = LocalDateTime.now();
        saveUser();
    }

    public  void  saveUser()  {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().serializeNulls().create();
        String s = gson.toJson(this);
        String path = "resources/saveUser";
        path +="/" + (id);
        path += ".txt";
        File file = new File(path);
        try {
            if (!file.exists())
                file.createNewFile();
            else {
                file.delete();
                file.createNewFile();
            }
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            gson.toJson(this, printStream);
            logger.info("file created for user");
            printStream.close();
        }catch (IOException e){
            System.out.println("error");
            logger.fatal("could not create file for user");
        }

    }



}

class LocalDateTimeSerializer implements JsonSerializer< LocalDateTime > {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDateTime));
    }
}