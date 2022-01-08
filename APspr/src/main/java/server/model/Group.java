package server.model;


import java.util.LinkedList;

public class Group {
    private String name;
   private LinkedList<String> usernames;


    public Group(String name) {
        this.name = name;
        usernames = new LinkedList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(LinkedList<String> usernames) {
        this.usernames = usernames;
    }


}
