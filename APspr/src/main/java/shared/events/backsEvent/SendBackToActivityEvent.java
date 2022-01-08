package shared.events.backsEvent;

import client.graphic.activity.ActivityView;

public class SendBackToActivityEvent {
    private ActivityView activityView;
    private String username;

    public SendBackToActivityEvent(ActivityView activityView, String username) {
        this.activityView = activityView;
        this.username = username;
    }

    public ActivityView getActivityView() {
        return activityView;
    }

    public void setActivityView(ActivityView activityView) {
        this.activityView = activityView;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
