package shared.events.settingsEvent;

import client.graphic.setting.LastSeenView;

public class SendLastSeenEvent {
    private LastSeenView seenView;

    public SendLastSeenEvent(LastSeenView seenView) {
        this.seenView = seenView;
    }

    public LastSeenView getSeenView() {
        return seenView;
    }

    public void setSeenView(LastSeenView seenView) {
        this.seenView = seenView;
    }
}
