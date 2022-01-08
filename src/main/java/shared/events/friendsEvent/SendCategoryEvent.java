package shared.events.friendsEvent;

import client.graphic.friendLists.category.CategoryView;

public class SendCategoryEvent {
    private CategoryView categoryView;

    public SendCategoryEvent(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    public CategoryView getCategoryView() {
        return categoryView;
    }

    public void setCategoryView(CategoryView categoryView) {
        this.categoryView = categoryView;
    }
}
