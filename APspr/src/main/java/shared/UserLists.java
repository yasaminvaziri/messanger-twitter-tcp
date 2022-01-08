package shared;

import java.util.LinkedList;

public class UserLists {
    private LinkedList<String> rejected_from;

    public UserLists(LinkedList<String> rejected_from) {
        this.rejected_from = rejected_from;
    }

    public LinkedList<String> getRejected_from() {
        return rejected_from;
    }

    public void setRejected_from(LinkedList<String> rejected_from) {
        this.rejected_from = rejected_from;
    }
}
