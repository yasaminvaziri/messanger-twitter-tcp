package server.controller;

import client.controller.MainController;
import shared.events.backsEvent.SendBackToFollowingsEvent;
import shared.events.categoryEvents.*;
import shared.events.friendsEvent.SendCategoryEvent;
import client.graphic.friendLists.Followings;
import client.graphic.friendLists.category.CategoryMembersView;
import client.graphic.friendLists.category.CategoryView;
import client.graphic.friendLists.category.MessageToCategoryView;
import server.model.Group;
import server.dataBase.Load;
import server.models.User;
import server.model.messaging.Chat;
import server.model.messaging.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.LinkedList;

public class CategoryAgent {
    private static final Logger logger = LogManager.getLogger(CategoryAgent.class);

    public void addCategory(SendAddCategoryEvent e){
        String username = e.getUsername();
        String name = e.getName();
        User user = Load.loadUser(username);
        Group group = new Group(name);
        user.getGroups().add(group);
        logger.info(user.getUsername() + " " + "added a category");
        user.updateLastSeen();
        user.saveUser();
    }
    public void goToCategories(SendCategoryEvent e){
        CategoryView categoryView = e.getCategoryView();
        categoryView.categoryPage(showGroups(MainController.username));

    }
    public void backToFollowings(SendBackToFollowingsEvent e){
        Followings followings = e.getFollowings();
        followings.followings(MainController.logicAgent.showFollowings(MainController.username));
    }
    public LinkedList<Group> showGroups(String username){
        User user = Load.loadUser(username);
        return user.getGroups();
    }
    public void deleteGroup(SendDeleteCategoryEvent e){
        String username = e.getUsername();
        Group grpName = e.getName();
        User user = Load.loadUser(username);
        user.getGroups().remove(grpName);
        logger.info(user.getUsername() + " " + "deleted a group");
        user.updateLastSeen();
        user.saveUser();
    }
    public void showCategoryMembers(SendCategoryMembersEvent e){
        CategoryMembersView categoryMembersView = e.getCategoryMembersView();
        LinkedList<String> members = e.getMembers();
        Group group = e.getGroup();
        categoryMembersView.categoryMembers(members,group);
    }
    public void addToGroup (SendAddNameEvent e){
        String you = e.getUsername();
        String member = e.getName();
        Group grpname = e.getGroup();
        User member_user = Load.loadUser(member);
        User user_you = Load.loadUser(you);
        if (member_user == null)
            return;
        for (Group group : user_you.getGroups())
            if (group.getName().equals(grpname.getName()))
                group.getUsernames().add(member);
        user_you.saveUser();
    }
    public LinkedList<String> showMembers(String username, Group group){
        LinkedList<String> usernames = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : group.getUsernames()){
            usernames.add(target);
        }
        return usernames;
    }
    public void deleteName(SendRemoveNameEvent e){
        String username = e.getUsername();
        String member = e.getName();
        Group group = e.getGroup();
        User user = Load.loadUser(username);
        User member_user = Load.loadUser(member);
        group.getUsernames().remove(member_user.getUsername());
        user.updateLastSeen();
        user.saveUser();
    }
    public void addMessageToCategory(SendMessageToCategoryEvent e){
        String username = e.getUsername();
        LinkedList<String> targets = e.getMembers();
        String text = e.getMessageText();
        File f = e.getF();
        User user = Load.loadUser(username);
        for (String member : targets) {
            User target = Load.loadUser(member);
            boolean flag = false;
            for (Chat chat : target.getChats()) {
                if (chat.user1.equals(user.getUsername()) || chat.user2.equals(user.getUsername())) {
                    Message message = new Message(user.getUsername(), text);
                    chat.getMessages().add(message);
                    if (f != null)
                        FileImage.saveMessagePhoto(f, message.getId());
                    message.saveMessage();
                    user.saveUser();
                    flag = true;
                }
            }
                if (flag == false){
                    Chat chat1 = new Chat(user.getUsername(),target.getUsername());
                    Message message = new Message(user.getUsername(),text);
                    chat1.getMessages().add(message);
                    user.getChats().add(chat1);
                    user.saveUser();
            }
            for (Chat chat : user.getChats()) {
                if (chat.user1.equals(target.getUsername()) || chat.user2.equals(target.getUsername())) {
                    Message message = new Message(user.getUsername(), text);
                    chat.getMessages().add(message);
                    if (f != null)
                        FileImage.saveMessagePhoto(f, message.getId());
                    message.saveMessage();
                    target.saveUser();
                    flag = true;
                }

            }
                if (flag == false){
                    Chat chat1 = new Chat(user.getUsername(),target.getUsername());
                    Message message = new Message(user.getUsername(),text);
                    chat1.getMessages().add(message);
                    target.getChats().add(chat1);
                    target.saveUser();
            }
        }
    }
    public void showCategoryMessage(SendCategoryMessageEvent e){
        MessageToCategoryView messageToCategoryView = e.getMessageToCategoryView();
        LinkedList<Message> messages = e.getMessages();
        LinkedList<String> members = e.getMembers();
        Group group = e.getGroup();
        messageToCategoryView.categoryChat(showMessageInCategory(MainController.username,group.getUsernames()),group.getUsernames(),group);
    }
    public LinkedList<Message> showMessageInCategory(String username, LinkedList<String> targets){
        User user = Load.loadUser(username);
        for (String tar : targets){
            User target = Load.loadUser(tar);
            for (Chat chat : user.getChats()){
                if (target.getUsername().equals(chat.user2) || target.getUsername().equals(chat.user1)){
                    return chat.getMessages();
                }
            }
        }
        return new LinkedList<>();
    }
    public void deleteInCategory(DeleteCategoryMessageEvent e){
        String username = e.getUsername();
        LinkedList<String> targets = e.getMembers();
        Message message = e.getMessage();
        User user = Load.loadUser(username);
        for (String target : targets){
            User target_user = Load.loadUser(target);
            for (Chat chat : user.getChats()){
                    for (int i = 0; i < chat.getMessages().size(); i++){
                        chat.getMessages().remove(message);
                        user.saveUser();
                        break;
                    }
            }
            for (Chat chat : target_user.getChats()) {
                for (int i = 0; i < chat.getMessages().size(); i++) {
                    chat.getMessages().remove(message);
                    target_user.saveUser();
                }
            }
        }
    }
    public void editInCategory(EditCategoryMessageEvent e){
        String username = e.getUsername();
        LinkedList<String> targets = e.getMembers();
        Message message = e.getMessage();
        String text = e.getNew_message();
        User user = Load.loadUser(username);
        for (String target : targets){
            User target_user = Load.loadUser(target);
            for (Chat chat : user.getChats()){
                for (int i = 0; i < chat.getMessages().size(); i++){
                    message.setMessage(text);
                    user.saveUser();
                    break;
                }
            }
            for (Chat chat : target_user.getChats()){
                for (int i = 0; i < chat.getMessages().size(); i++){
                    message.setMessage(text);
                    target_user.saveUser();
                }
            }
        }
    }
}
