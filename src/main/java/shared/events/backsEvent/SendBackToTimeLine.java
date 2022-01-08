package shared.events.backsEvent;

import client.graphic.timeLine.TimeLineView;

public class SendBackToTimeLine {
    private TimeLineView timeLineView;

    public SendBackToTimeLine(TimeLineView timeLineView) {
        this.timeLineView = timeLineView;
    }

    public TimeLineView getTimeLineView() {
        return timeLineView;
    }

    public void setTimeLineView(TimeLineView timeLineView) {
        this.timeLineView = timeLineView;
    }
}

