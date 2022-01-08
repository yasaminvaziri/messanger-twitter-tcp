package shared.events.backsEvent;

import client.graphic.home.HomePageView;

public class SendBackToHomeEvent {
    private HomePageView homePageView;

    public SendBackToHomeEvent(HomePageView homePageView) {
        this.homePageView = homePageView;
    }

    public HomePageView getHomePageView() {
        return homePageView;
    }

    public void setHomePageView(HomePageView homePageView) {
        this.homePageView = homePageView;
    }
}
