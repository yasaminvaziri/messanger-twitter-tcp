package shared.events.friendsEvent;

import client.graphic.friendLists.MuteList;

public class SendMuteListEvent {
    private MuteList muteList;

    public SendMuteListEvent(MuteList muteList) {
        this.muteList = muteList;
    }

    public MuteList getMuteList() {
        return muteList;
    }

    public void setMuteList(MuteList muteList) {
        this.muteList = muteList;
    }
}
