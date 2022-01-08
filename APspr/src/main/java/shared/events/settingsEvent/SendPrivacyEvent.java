package shared.events.settingsEvent;

import client.graphic.setting.PrivacyView;

public class SendPrivacyEvent {
    private PrivacyView privacyView;

    public SendPrivacyEvent(PrivacyView privacyView) {
        this.privacyView = privacyView;
    }

    public PrivacyView getPrivacyView() {
        return privacyView;
    }

    public void setPrivacyView(PrivacyView privacyView) {
        this.privacyView = privacyView;
    }
}
