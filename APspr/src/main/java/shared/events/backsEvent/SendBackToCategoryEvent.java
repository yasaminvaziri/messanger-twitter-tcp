package shared.events.backsEvent;

import client.graphic.friendLists.category.CategoryView;

public class SendBackToCategoryEvent {
    private CategoryView categoryView;
    private String username;

    public SendBackToCategoryEvent(CategoryView categoryView, String username) {
        this.categoryView = categoryView;
        this.username = username;
    }

    public CategoryView getCategoryView() {
        return categoryView;
    }

    public void setCategoryView(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
