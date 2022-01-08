package shared.events.backsEvent;

import client.graphic.welcome.MenuView;

public class SendBackToMenuEvent {
    private MenuView menuView;

    public SendBackToMenuEvent(MenuView menuView) {
        this.menuView = menuView;
    }

    public MenuView getMenuView() {
        return menuView;
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }
}
