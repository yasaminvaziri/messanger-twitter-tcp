package client.listener;

import shared.events.activityEvents.SendRejectedFromEvent;
import shared.events.activityEvents.SendRequestViewEvent;
import shared.events.activityEvents.SendRequestedToEvent;
import shared.events.activityEvents.SendUnfollowedFromEvent;
import shared.events.activityEvents.SendAcceptEvent;
import shared.events.activityEvents.SendRejectNotifyEvent;
import shared.events.activityEvents.SendRejectSilentEvent;
import client.controller.MainController;

public class ActivityListener {
    public void requests(SendRequestViewEvent e){
        MainController.logicAgent.goToRequestsView(e);
    }
    public void requestedTo(SendRequestedToEvent e){
        MainController.logicAgent.goToRequestedToView(e);
    }
    public void unfollowedFrom(SendUnfollowedFromEvent e){
        MainController.logicAgent.goToUnfollowedView(e);
    }
    public void rejectedFrom(SendRejectedFromEvent e){
        MainController.logicAgent.goToRejectedFrom(e);
    }
    public void accept(SendAcceptEvent e){
        MainController.logicAgent.accept(e);
    }
    public void rejectSilently(SendRejectSilentEvent e){
        MainController.logicAgent.rejectSilently(e);
    }
    public void reject_notify(SendRejectNotifyEvent e){
        MainController.logicAgent.reject_notify(e);
    }


}
