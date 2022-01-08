package shared.events.profileEvent;

import client.graphic.profile.ShowProfileView;

public class SeeProfileEvent {
    private String target;
    private ShowProfileView showProfileView;

    public SeeProfileEvent(String target,ShowProfileView showProfileView) {
        this.target = target;
        this.showProfileView = showProfileView;

    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public ShowProfileView getShowProfileView() {
        return showProfileView;
    }

    public void setShowProfileView(ShowProfileView showProfileView) {
        this.showProfileView = showProfileView;
    }
}
