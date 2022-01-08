package server.model.messaging;

import java.util.LinkedList;

public class Chat {
   public String user1;
   public String user2;
   public LinkedList<Message>messages;


    public Chat(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
        messages = new LinkedList<>();
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }

    public int getUnread(String username){
        int cnt = 0;
        for (Message message : messages){
            if (!message.getSender().equals(username) && message.seen == false){
                cnt++;
            }
        }
        return cnt;
    }

}
