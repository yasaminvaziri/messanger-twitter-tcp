package shared.events.backsEvent;

import client.graphic.explorer.ExplorerView;

public class SendBackToExplorerEvent {
    private ExplorerView explorerView;
    private String username;

    public SendBackToExplorerEvent(ExplorerView explorerView, String username) {
        this.explorerView = explorerView;
        this.username = username;
    }

    public ExplorerView getExplorerView() {
        return explorerView;
    }

    public void setExplorerView(ExplorerView explorerView) {
        this.explorerView = explorerView;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
