package shared.events.backsEvent;

import client.graphic.setting.SettingView;

public class SendBackToSettingsEvent {
    private SettingView settingView;

    public SendBackToSettingsEvent(SettingView settingView) {
        this.settingView = settingView;
    }

    public SettingView getSettingView() {
        return settingView;
    }

    public void setSettingView(SettingView settingView) {
        this.settingView = settingView;
    }
}
