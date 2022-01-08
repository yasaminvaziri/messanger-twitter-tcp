package shared.events.friendsEvent;

import client.graphic.friendLists.ReportList;

public class SendReportListEvent {
    private ReportList reportList;

    public SendReportListEvent(ReportList reportList) {
        this.reportList = reportList;
    }

    public ReportList getReportList() {
        return reportList;
    }

    public void setReportList(ReportList reportList) {
        this.reportList = reportList;
    }
}
