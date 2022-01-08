package client.listener;

import shared.events.backsEvent.SendBackToCategoryEvent;
import shared.events.backsEvent.SendBackToFollowingsEvent;
import shared.events.categoryEvents.*;
import client.controller.MainController;

public class CategoryListener {
    public void deleteCategory(SendDeleteCategoryEvent e){
        MainController.categoryAgent.deleteGroup(e);
    }
    public void showMembers(SendCategoryMembersEvent e){
        MainController.categoryAgent.showCategoryMembers(e);
    }
    public void showMessageInCategory(SendCategoryMessageEvent e){
        MainController.categoryAgent.showCategoryMessage(e);
    }
    public void addCategory(SendAddCategoryEvent e){
        MainController.categoryAgent.addCategory(e);
    }
    public void backToFollowings(SendBackToFollowingsEvent e){
        MainController.categoryAgent.backToFollowings(e);
    }
    public void removeUsername(SendRemoveNameEvent e){
        MainController.categoryAgent.deleteName(e);
    }
    public void addMember(SendAddNameEvent e){
        MainController.categoryAgent.addToGroup(e);
    }
    public void backToCategory(SendBackToCategoryEvent e){
        MainController.backsAgent.backToCategory(e);
    }
    public void deleteMessageInCategory(DeleteCategoryMessageEvent e){
        MainController.categoryAgent.deleteInCategory(e);
    }
    public void editMessageInCategory(EditCategoryMessageEvent e){
        MainController.categoryAgent.editInCategory(e);
    }
    public void addMessageToCategory(SendMessageToCategoryEvent e){
        MainController.categoryAgent.addMessageToCategory(e);
    }
}
