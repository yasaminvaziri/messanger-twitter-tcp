package shared.events.loginEvents;

import client.graphic.welcome.RegisterView;



public class LoginPanelRegisterButton {
    private RegisterView registerView;

    public LoginPanelRegisterButton(RegisterView registerView){
        this.registerView = registerView;
    }

    public RegisterView getRegisterView() {
        return registerView;
    }

    public void setRegisterView(RegisterView registerView) {
        this.registerView = registerView;
    }
}
