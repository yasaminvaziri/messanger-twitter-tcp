package client.listener;

import shared.events.messageEvents.*;
import shared.events.profileEvent.SeeProfileEvent;
import client.controller.MainController;

public class MessageListener {
    public void savedMessage(SendSavedMessageViewEvent e){
        MainController.messageAgent.goToSavedMessages(e);
    }
    public void goToChats(SendChatViewEvent e){
        MainController.messageAgent.goToChats(e);
    }
    public void goToGroups(SendGroupsViewEvent e){
        MainController.messageAgent.goToGroups(e);
    }
    //////
    public void deleteSavedMessage(DeleteSavedMessageEvent e){
        MainController.messageAgent.deleteSavedMessage(e);
    }
    public void editSavedMessage(EditSavedMessageEvent e){
        MainController.messageAgent.editSavedMessage(e);
    }
    public void addMessageToSaved(AddToSavedEvent e){
        MainController.messageAgent.addMessageToSaved(e);
    }
    ///////
    public void addGroupName(AddGroupNameEvent e){
        MainController.messageAgent.addGroupName(e);
    }
    public void leftFromGroup(LeftGroupEvent e){
        MainController.messageAgent.leftFromGroup(e);
    }
    public void goToGroupChat(SendGroupChatViewEvent e){
        MainController.messageAgent.goToGroupConversation(e);
    }
    /////////
    public void addNameToChat(AddNameToChatsEvent e){
        MainController.messageAgent.addNameToChats(e);
    }
    public void showProfile(SeeProfileEvent e){
        MainController.messageAgent.showProfile(e);
    }
    public void goToConversation(SendConversationViewEvent e){
        MainController.messageAgent.showConversation(e);
    }



    //////
    public void deleteInChat(DeleteChatMessageEvent e){
        MainController.messageAgent.deleteMessage(e);
    }
    public void editInChat(EditInChatEvent e){
        MainController.messageAgent.editMessage(e);
    }
    public void addMessageInChat(SendChatMessageEvent e){
        MainController.messageAgent.addMessage(e);
    }
    ////
    public void deleteInGroup(SendDeleteInGroupEvent e){
        MainController.messageAgent.deleteInGroup(e);
    }
    public void editInGroup(EditGroupMessageEvent e){
        MainController.messageAgent.editInGroup(e);
    }
    public void addMessageToGroup(SendMessageGroupEvent e){
        MainController.messageAgent.addMessageToGroup(e);
    }
    public void addMemberToGroup(SendAddGroupMemberEvent e){
        MainController.messageAgent.addMemberToGroup(e);
    }

}
