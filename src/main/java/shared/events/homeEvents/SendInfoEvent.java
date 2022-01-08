package shared.events.homeEvents;

import client.graphic.home.InfoView;

public class SendInfoEvent {
    private InfoView infoView;

    public SendInfoEvent(InfoView infoView) {
        this.infoView = infoView;
    }

    public InfoView getInfoView() {
        return infoView;
    }

    public void setInfoView(InfoView infoView) {
        this.infoView = infoView;
    }
}
