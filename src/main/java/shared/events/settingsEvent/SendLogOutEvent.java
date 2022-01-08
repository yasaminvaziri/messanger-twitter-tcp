package shared.events.settingsEvent;

import client.graphic.welcome.LoginView;

public class SendLogOutEvent {
    private LoginView loginView;

    public SendLogOutEvent(LoginView loginView) {
        this.loginView = loginView;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }
}
