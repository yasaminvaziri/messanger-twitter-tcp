package shared.lists;

import server.models.User;
import shared.UserLists;

import java.util.LinkedList;

public class RejectedFrom {
    public LinkedList<UserLists> rejected;

    public RejectedFrom(LinkedList<UserLists> users) {
        rejected = new LinkedList<>();
        for (UserLists user:users) {
            rejected.add(new UserLists(user.getRejected_from()));
        }

    }
}
