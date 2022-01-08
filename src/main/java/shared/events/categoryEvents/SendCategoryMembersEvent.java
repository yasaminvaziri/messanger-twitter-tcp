package shared.events.categoryEvents;

import client.graphic.friendLists.category.CategoryMembersView;
import server.model.Group;

import java.util.LinkedList;

public class SendCategoryMembersEvent {
    private CategoryMembersView categoryMembersView;
    private LinkedList<String> members;
    private Group group;

    public SendCategoryMembersEvent(CategoryMembersView categoryMembersView, LinkedList<String> members, Group group) {
        this.members = members;
        this.group = group;
        this.categoryMembersView = categoryMembersView;
    }

    public LinkedList<String> getMembers() {
        return members;
    }

    public void setMembers(LinkedList<String> members) {
        this.members = members;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public CategoryMembersView getCategoryMembersView() {
        return categoryMembersView;
    }

    public void setCategoryMembersView(CategoryMembersView categoryMembersView) {
        this.categoryMembersView = categoryMembersView;
    }
}
