package shared.events.welcomeEvents;

import client.graphic.explorer.ExplorerView;
import client.graphic.home.HomePageView;
import client.graphic.messaging.MessagingView;
import client.graphic.setting.SettingView;
import client.graphic.timeLine.TimeLineView;

public class SendMenuEvent {
    private HomePageView homePageView;
    private TimeLineView timeLineView;
    private ExplorerView explorerView;
    private SettingView settingView;
    private MessagingView messagingView;

    public SendMenuEvent(HomePageView homepage){
        this.homePageView = homepage;
    }
    public SendMenuEvent(TimeLineView timeLine){
        this.timeLineView = timeLine;
    }
    public SendMenuEvent(ExplorerView explorer){
        this.explorerView = explorer;
    }
    public SendMenuEvent(SettingView setting){
        this.settingView = setting;
    }
    public SendMenuEvent(MessagingView messaging){
        this.messagingView = messaging;
    }


    public HomePageView getHomePageView() {
        return homePageView;
    }

    public void setHomePageView(HomePageView homePageView) {
        this.homePageView = homePageView;
    }

    public TimeLineView getTimeLineView() {
        return timeLineView;
    }

    public void setTimeLineView(TimeLineView timeLineView) {
        this.timeLineView = timeLineView;
    }

    public ExplorerView getExplorerView() {
        return explorerView;
    }

    public void setExplorerView(ExplorerView explorerView) {
        this.explorerView = explorerView;
    }

    public SettingView getSettingView() {
        return settingView;
    }

    public void setSettingView(SettingView settingView) {
        this.settingView = settingView;
    }

    public MessagingView getMessagingView() {
        return messagingView;
    }

    public void setMessagingView(MessagingView messagingView) {
        this.messagingView = messagingView;
    }
}
