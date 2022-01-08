package server.controller;

import client.controller.MainController;
import shared.events.messageEvents.*;
import shared.events.profileEvent.SeeProfileEvent;
import shared.events.timeLineEvents.SavedMessageEvent;
import shared.events.timeLineEvents.SendForwardEvent;
import client.graphic.messaging.*;
import client.graphic.profile.ShowProfileView;
import server.model.Groups;
import server.model.Tweet;
import server.dataBase.Load;
import server.models.User;
import server.model.messaging.Chat;
import server.model.messaging.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.LinkedList;

public class MessageAgent {
    private static final Logger logger = LogManager.getLogger(MessageAgent.class);

    public void addMessageToSaved(AddToSavedEvent e){
        File f = e.getF();
        String sender = e.getUsername();
        String text = e.getContent();
        User user = Load.loadUser(sender);
        Message message = new Message(sender,text);
        message.setSeen(true);
        user.getSavedMessages().add(message);
        if (f != null)
            FileImage.saveMessagePhoto(f,message.getId());
        message.saveMessage();
        logger.info(user.getUsername() + "added a message for herself");
        user.updateLastSeen();
        user.saveUser();
    }
    public void addTweetToSaved(SavedMessageEvent e){
        String sender = e.getSender();
        Tweet tweet = e.getTweet();
        User user = Load.loadUser(sender);
        Message message = new Message(sender, tweet);
        message.setSeen(true);
        user.getSavedMessages().add(message);
        logger.info(user.getUsername() + " " + "saved a tweet");
        user.updateLastSeen();
        user.saveUser();
    }
    public LinkedList<Message> showSavedMessages(String username){
        LinkedList<Message> messages = new LinkedList<>();
        User user = Load.loadUser(username);
        for (Message target : user.getSavedMessages()){
            messages.add(target);
        }
        return messages;
    }
    public void deleteSavedMessage(DeleteSavedMessageEvent e){
        String username = e.getUsername();
        Message message = e.getMessage();
        User user = Load.loadUser(username);
        user.getSavedMessages().remove(message);
        logger.info(user.getUsername() + " " + "deleted a message");
        user.updateLastSeen();
        user.saveUser();
    }
    public void editSavedMessage(EditSavedMessageEvent e){
        String text = e.getText();
        Message message = e.getMessage();
        User user = Load.loadUser(message.getSender());
        message.setMessage(text);
        logger.info(user.getUsername() + " " + "edited a message");
        user.updateLastSeen();
        user.saveUser();
    }
    public String addNameToChats(AddNameToChatsEvent e) {
        String you = e.getUsername();
        String target = e.getName();
        User user_you = Load.loadUser(you);
        User target_user = Load.loadUser(target);
        if (target_user != null && target_user.getFollowers().contains(user_you.getUsername())
                && user_you.getFollowers().contains(target_user.getUsername())){
            Chat chat = new Chat(user_you.getUsername(), target_user.getUsername());
            user_you.getChats().add(chat);
            target_user.getChats().add(chat);
            user_you.updateLastSeen();
            user_you.saveUser();
            target_user.saveUser();
            return target;
        }
        else
            return null;
    }
    public LinkedList<Chat> showNames(String username){

        return Load.loadUser(username).getChats();
    }

    public String addGroupName(AddGroupNameEvent e){
        String you = e.getUsername();
        String groupName = e.getName();
        User user_you = Load.loadUser(you);
        Groups groups = new Groups(groupName);
        user_you.getGroupsName().add(groups);
        groups.getUsernames().add(user_you.getUsername());
        user_you.updateLastSeen();
        user_you.saveUser();
        return groupName;
    }
    public LinkedList<Groups> showGroupsName(String username){
        return Load.loadUser(username).getGroupsName();
    }


    public LinkedList<Message> showMessagesInChat(String username, String targetUser){
        User user = Load.loadUser(username);
        for (Chat chat : user.getChats()){
            if (targetUser.equals(chat.user2) || targetUser.equals(chat.user1))
                return chat.getMessages();
        }
        return new LinkedList<>();
    }

    public LinkedList<Message> showMessageInGroup(String username, LinkedList<String> targets){
        User user = Load.loadUser(username);
            for (Groups groups : user.getGroupsName()) {
                    return groups.getMessages();
            }

        return new LinkedList<>();
    }

    public void addMessageToGroup(SendMessageGroupEvent e){
        String username = e.getUsername();
        LinkedList<String> targets = e.getMembers();
        String text = e.getMessageText();
        File f = e.getF();
        User user = Load.loadUser(username);
        if (targets != null){
          for (String receiver : targets) {
            User target = Load.loadUser(receiver);
            for (Groups groups : target.getGroupsName()) {
                Message message = new Message(user.getUsername(), text);
                groups.getMessages().add(message);
                if (f != null)
                    FileImage.saveMessagePhoto(f, message.getId());
                message.saveMessage();
                target.saveUser();
            }
         }
        }else
            return;

    }

    public void addMessage(SendChatMessageEvent e) {
        String username = e.getUsername();
        String target = e.getTarget();
        String text = e.getMessageText();
        File f = e.getF();
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        for (Chat chat : user.getChats()){
            if (chat.user1.equals(user.getUsername()) || chat.user2.equals(user.getUsername())){
                Message message = new Message(user.getUsername(),text);
                chat.getMessages().add(message);
                if (f != null)
                    FileImage.saveMessagePhoto(f,message.getId());
                message.saveMessage();
                logger.info(user.getUsername() + " " + "added a message");
                user.saveUser();
                break;
            }
        }
        for (Chat chat : targetUser.getChats()) {
            if (chat.user1.equals(targetUser.getUsername()) || chat.user2.equals(targetUser.getUsername())) {
                Message message = new Message(user.getUsername(), text);
                chat.getMessages().add(message);
                if (f != null)
                    FileImage.saveMessagePhoto(f,message.getId());
                logger.info(user.getUsername() + " " + "added a message");
                message.saveMessage();
                targetUser.saveUser();
            }
        }
    }
    public void deleteInGroup(SendDeleteInGroupEvent e){
        String username = e.getUsername();
        LinkedList<String> targets = e.getMembers();
        Message message = e.getMessage();
        User user = Load.loadUser(username);
        for (String target : targets){
            User target_user = Load.loadUser(target);
            for (Groups groups : user.getGroupsName()){
                if (groups.getUsernames().contains(target_user.getUsername())){
                    for (int i = 0; i < groups.getMessages().size(); i++){
                        groups.getMessages().remove(message);
                        user.saveUser();
                        break;
                    }
                }
            }
            for (Groups groups : target_user.getGroupsName()){
                if (groups.getUsernames().contains(target_user.getUsername())){
                    for (int i = 0; i < groups.getMessages().size(); i++){
                        groups.getMessages().remove(message);
                        target_user.saveUser();
                    }
                }
            }
        }
    }

    public void deleteMessage(DeleteChatMessageEvent e){
        String username = e.getUsername();
        String target = e.getTarget();
        Message message = e.getMessage();
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        for (Chat chat : user.getChats()){
            if (chat.user1.equals(user.getUsername()) || chat.user2.equals(user.getUsername())) {
                for (int i = 0; i < chat.getMessages().size(); i++) {
                    chat.getMessages().remove(message);
                    user.saveUser();
                    break;
                }
            }
        }

        for (Chat chat : targetUser.getChats()){
            if (chat.user1.equals(user.getUsername()) || chat.user2.equals(user.getUsername())) {
                for (int i = 0; i < chat.getMessages().size(); i++) {
                    chat.getMessages().remove(message);
                    targetUser.saveUser();

                }
            }
        }

    }
    public void editInGroup(EditGroupMessageEvent e){
        String username = e.getUsername();
        LinkedList<String> targets = e.getMembers();
        String text = e.getNew_message();
        Message message = e.getMessage();
        User user = Load.loadUser(username);
        for (String target : targets){
            User target_user = Load.loadUser(target);
            for (Groups groups : target_user.getGroupsName()){
                for (int i = 0; i < groups.getMessages().size(); i++){
                    message.setMessage(text);
                    target_user.saveUser();
                }
            }
        }
    }


    public void editMessage(EditInChatEvent e) {
        String username = e.getUsername();
        String target = e.getTarget();
        String text = e.getNew_message();
        Message message = e.getMessage();
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        for (Chat chat : user.getChats()) {
            for (int i = 0; i < chat.getMessages().size(); i++) {
                message.setMessage(text);
                user.saveUser();
            }
        }
        for (Chat chat : targetUser.getChats()) {
            for (int i = 0; i < chat.getMessages().size(); i++) {
                message.setMessage(text);
                targetUser.saveUser();
            }
        }
    }
    public void addMemberToGroup(SendAddGroupMemberEvent e){
        String username = e.getUsername();
        String target = e.getTarget();
        Groups groups = e.getGroups();
        User user = Load.loadUser(username);
        User target_user = Load.loadUser(target);
        if (user.getFollowings().contains(target_user.getUsername()) && !user.getBlockList().contains(target_user.getUsername())){
                target_user.getGroupsName().add(groups);
                groups.getUsernames().add(target_user.getUsername());
                user.saveUser();
                target_user.saveUser();
        }
    }
    public void leftFromGroup(LeftGroupEvent e) {
        String username = e.getUsername();
        Groups groups = e.getGroups();
        User user = Load.loadUser(username);
        user.getGroupsName().remove(groups);
            user.saveUser();

    }


    public void forwardTweet(SendForwardEvent e){
        String username = e.getUsername();
        String target = e.getTarget();
        Tweet tweet = e.getTweet();
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        if (targetUser != null) {
            if (user.getFollowings().contains(targetUser.getUsername())) {
                for (Chat chat : user.getChats()) {
                    if (chat.user2.equals(targetUser.getUsername()) || chat.user1.equals(targetUser.getUsername())) {
                        Message message = new Message(user.getUsername(), tweet);
                        chat.getMessages().add(message);
                        message.saveMessage();
                        user.saveUser();
                        break;
                    }
                }
                for (Chat chat : targetUser.getChats()) {
                    if (chat.user2.equals(user.getUsername()) || chat.user1.equals(user.getUsername())) {
                        Message message = new Message(username, tweet);
                        message.saveMessage();
                        chat.getMessages().add(message);
                        targetUser.saveUser();
                        return;

                    }
                }

                Chat chat = new Chat(user.getUsername(), targetUser.getUsername());
                user.getChats().add(chat);
                targetUser.getChats().add(chat);
                Message message = new Message(user.getUsername(), tweet);
                user.getChats().get(user.getChats().size() - 1).getMessages().add(message);
                targetUser.getChats().get(targetUser.getChats().size() - 1).getMessages().add(message);
                user.saveUser();
                targetUser.saveUser();
            }
        }else
            return;
    }
    public void goToSavedMessages(SendSavedMessageViewEvent e){
        SavedMessagesView savedMessagesView = e.getSavedMessagesView();
        savedMessagesView.savedMessages(showSavedMessages(MainController.username));
    }
    public void goToChats(SendChatViewEvent e){
        ChatsView chatsView = e.getChatsView();
        chatsView.chats(showNames(MainController.username));
    }
    public void goToGroups(SendGroupsViewEvent e){
        GroupsView groupsView = e.getGroupsView();
        groupsView.groupView(showGroupsName(MainController.username));
    }
    public void showProfile(SeeProfileEvent e){
        String target = e.getTarget();
        ShowProfileView showProfileView = e.getShowProfileView();
        User user = Load.loadUser(target);
            showProfileView.showProfile(user.getUsername());

    }
    public void goToGroupConversation(SendGroupChatViewEvent e){
        GroupConversationView groupConversationView = e.getGroupConversationView();
        LinkedList<Message> messages = e.getMessages();
        LinkedList<String> members = e.getMembers();
        Groups groups = e.getGroups();
        String username = e.getUsername();
        groupConversationView.groupChat(showMessageInGroup(MainController.username,members),groups.getUsernames(),groups);
    }
    public void showConversation(SendConversationViewEvent e){
        ConversationView conversationView = e.getConversationView();
        String username = e.getUsername();
        User you = Load.loadUser(username);
        String target = e.getTarget();
        User other = Load.loadUser(target);
        conversationView.messages(showMessagesInChat(you.getUsername(),other.getUsername()),other.getUsername());
    }
}
