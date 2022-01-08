package server.controller;


import client.controller.MainController;
import client.controller.ProfileController;
import shared.UserInfo;
import shared.events.activityEvents.*;
import shared.events.friendsEvent.*;
import shared.events.homeEvents.SendActivityEvent;
import shared.events.homeEvents.SendFriendsEvent;
import shared.events.homeEvents.SendInfoEvent;
import shared.events.settingsEvent.SendLogOutEvent;
import client.graphic.activity.*;
import client.graphic.friendLists.*;
import client.graphic.home.InfoView;
import client.graphic.welcome.LoginView;
import server.dataBase.Load;
import server.models.User;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.LinkedList;


public class LogicAgent {
    private static final Logger logger = LogManager.getLogger(LogicAgent.class);

    public String follow(String username, String target, String warn){
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        if (targetUser.isPublic()) {
            if (!user.getFollowings().contains(targetUser.getUsername()) &&
                    !targetUser.getFollowers().contains(user.getUsername())
                    && !user.getBlockList().contains(targetUser.getUsername())
                    && !user.getReportedU().contains(targetUser.getUsername())
                    && targetUser.getId() != user.getId()) {
                user.getFollowings().add(targetUser.getUsername());
                targetUser.getFollowers().add(user.getUsername());
                logger.info(user.getUsername() + " " + "followed" + " " + targetUser.getUsername());
                user.updateLastSeen();
                user.saveUser();
                targetUser.saveUser();
                warn = "you just followed the user";
            }
            else
                warn = "cant follow the user";
        }
        else if (!targetUser.isPublic()){
            if (!user.getFollowings().contains(targetUser.getUsername()) &&
                    !targetUser.getFollowers().contains(user.getUsername())
                    && !user.getBlockList().contains(targetUser.getUsername())
                    && !user.getReportedU().contains(targetUser.getUsername()) && targetUser.getId() != user.getId()){
                user.getHaveSentRequest().add(targetUser.getUsername());
                targetUser.getRequests().add(user.getUsername());
                logger.info(user.getUsername() + " " + "sent request to" + " " + targetUser.getUsername());
                user.updateLastSeen();
                user.saveUser();
                targetUser.saveUser();
                warn = "your request was sent";
            }
            else
                warn = "cant send request";
        }
        return warn;
    }
    public LinkedList<String> showRequests(String username){
        LinkedList<String> req = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : user.getRequests()){
            User targetUser = Load.loadUser(target);
            if (targetUser != null){
                req.add(targetUser.getUsername());
            }
        }
        return req;
    }
    public LinkedList<String> showSentRequest(String username){
        LinkedList<String> req = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : user.getHaveSentRequest()){
            User targetUser = Load.loadUser(target);
            if (targetUser != null){
                req.add(targetUser.getUsername());
            }
        }
        return req;
    }
    public void goToUnfollowedView(SendUnfollowedFromEvent e){
        UnfollowedFrom unfollowedFrom = e.getUnfollowedFrom();
        unfollowedFrom.unfollowedFrom(showUnfollowedFrom(MainController.username));
    }
    public void goToRequestsView(SendRequestViewEvent e){
        RequestsView requestsView = e.getRequestsView();
        requestsView.request(showRequests(MainController.username));
    }
    public void goToRequestedToView(SendRequestedToEvent e){
        RequestedToView requestedToView = e.getRequestedToView();
        requestedToView.requestTo(showSentRequest(MainController.username));
    }
    public void goToRejectedFrom(SendRejectedFromEvent e){
        new RejectedFromView();
        RejectedFromView rejectedFromView = e.getRejectedFromView();
       rejectedFromView.rejectedFrom(showRejectedFrom(MainController.username));
    }

    public LinkedList<String> showUnfollowedFrom(String username){
        LinkedList<String> list = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : user.getUnfollowed_from()){
            User targetUser = Load.loadUser(target);
            if (targetUser != null){
                list.add(targetUser.getUsername());
            }
        }
        return list;
    }

    public String unFollow(String you, String target, String warn){
        User user_you = Load.loadUser(you);
        User target_user = Load.loadUser(target);

            if (user_you.getFollowings().contains(target_user.getUsername())) {
                user_you.getFollowings().remove(target_user.getUsername());
                target_user.getUnfollowed_from().add(user_you.getUsername());
                target_user.getFollowers().remove(user_you.getUsername());
                logger.info(user_you.getUsername() + " " + "unfollowed" + " " +target_user.getUsername());
                    target_user.saveUser();
                    user_you.updateLastSeen();
                    user_you.saveUser();

                if (user_you.getMuted().contains(target_user.getUsername())) {
                    user_you.getMuted().remove(target_user.getUsername());
                    user_you.updateLastSeen();
                    user_you.saveUser();
                }
                warn = "you just unfollowed user";
            } else
                warn = "you need to follow the user first";

        return warn;
    }
    public String block(String you, String target, String warn){
        User user_you = Load.loadUser(you);
        User target_user = Load.loadUser(target);
        if (!user_you.getBlockList().contains(target_user.getUsername())){
            user_you.getBlockList().add(target_user.getUsername());
            logger.info(user_you.getUsername() + " " + "blocked" + " " + target_user.getUsername());
            user_you.updateLastSeen();
            user_you.saveUser();

            if (user_you.getFollowers().contains(target_user.getUsername())) {
                user_you.getFollowers().remove(target_user.getUsername());
                target_user.getFollowings().remove(user_you.getUsername());
                user_you.saveUser();
                target_user.saveUser();
            }
             if (user_you.getFollowings().contains(target_user.getUsername())) {
                user_you.getFollowings().remove(target_user.getUsername());
                target_user.getFollowers().remove(user_you.getUsername());
                if (user_you.getMuted().contains(target_user.getUsername())){
                    user_you.getMuted().remove(target_user.getUsername());
                    user_you.saveUser();
                }
                user_you.updateLastSeen();
                user_you.saveUser();
                target_user.saveUser();
            }
            warn = "user blocked";
        }
        else
            warn = "user is already blocked";
        return warn;
    }
    public String unBlock(String you, String target, String warn){
        User user_you = Load.loadUser(you);
        User target_user = Load.loadUser(target);
        if (user_you.getBlockList().contains(target_user.getUsername())){
            user_you.getBlockList().remove(target_user.getUsername());
            logger.info(user_you.getUsername() + " " + "unblocked" + " " + target_user.getUsername());
            user_you.updateLastSeen();
            warn = "user unblocked";
            user_you.saveUser();
        }
        else
            warn = "user is not blocked";
        return warn;
    }
    public String muteUser(String you, String target, String warn){
        User user_you = Load.loadUser(you);
        User target_user = Load.loadUser(target);
        if (!user_you.getMuted().contains(target_user.getUsername())){
            user_you.getMuted().add(target_user.getUsername());
            logger.info(user_you.getUsername() + " " + "muted" + " " + target_user.getUsername());
            user_you.saveUser();
            user_you.updateLastSeen();
            warn = "user muted";

        }
        else
            warn = "user is already muted";
        return warn;
    }
    public String unMuteUser(String you, String target, String warn){
        User user_you = Load.loadUser(you);
        User target_user = Load.loadUser(target);
        if (user_you.getMuted().contains(target_user.getUsername())){
            user_you.getMuted().remove(target_user.getUsername());
            logger.info(user_you.getUsername() + " " + "un muted" + " " + target_user.getUsername());
            user_you.updateLastSeen();
            user_you.saveUser();
            warn = "user is not mute anymore";
        }
        else
            warn = "user is not mute";
        return warn;
    }
    public String reportUser(String you, String target, String warn){
        User user_you = Load.loadUser(you);
        User target_user = Load.loadUser(target);
        if (!user_you.getReportedU().contains(target_user.getUsername())) {
            user_you.getReportedU().add(target_user.getUsername());
            logger.info(user_you.getUsername() + " " + "reported" + " " + target_user.getUsername());
            user_you.saveUser();
            if (user_you.getFollowings().contains(target_user.getUsername())){
                user_you.getFollowings().remove(target_user.getUsername());
                target_user.getFollowers().remove(user_you.getUsername());
                user_you.updateLastSeen();
                user_you.saveUser();
                target_user.saveUser();
            }
            else if (target_user.getFollowers().contains(user_you.getUsername())){
                target_user.getFollowers().remove(user_you.getUsername());
                user_you.getFollowings().remove(target_user.getUsername());
                user_you.updateLastSeen();
                user_you.saveUser();
                target_user.saveUser();
            }
            warn = "user reported";
        }
        else
            warn = "user is already reported";
        return warn;
    }
    public void goToActivityView(SendActivityEvent e){
        ActivityView activityView = e.getActivityView();
        activityView.activity(showFollowers(MainController.username));
    }
    public void goToInfo(SendInfoEvent e){
        new ProfileController();
        //new InfoView();
        //InfoView infoView = e.getInfoView();
        //infoView.infoPage();
        //infoView.infoPage();
    }
    public void goToFriends(SendFriendsEvent e){
        Friends friends = e.getFriends();
        friends.friendsPage(showFollowers(MainController.username));
    }
    public void goToFollowersView(SendFollowersEvent e){
        Followers followers = e.getFollowers();
        followers.followers(showFollowers(MainController.username));
    }
    public void goToBlockListView(SendBlockListEvent e){
        BlockList blockList = e.getBlockList();
        blockList.blockList(showBlockList(MainController.username));
    }
    public void showMuteListView(SendMuteListEvent e){
        MuteList muteList = e.getMuteList();
        muteList.muteList(showMuteList(MainController.username));
    }
    public void showReportListView(SendReportListEvent e){
        ReportList reportList = e.getReportList();
        reportList.reportList(showReportList(MainController.username));
    }
    public void showFollowingsView(SendFollowingsEvent e){
        Followings followings = e.getFollowings();
        followings.followings(showFollowings(MainController.username));
    }

    public LinkedList<String> showFollowers(String username){
        LinkedList<String> followers = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : user.getFollowers()){
            User targetUser = Load.loadUser(target);
            followers.add(targetUser.getUsername());
        }
        return followers;
    }
    public LinkedList<String> showBlockList(String username){
        LinkedList<String> blockList = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : user.getBlockList()){
            User targetUser = Load.loadUser(target);
            blockList.add(targetUser.getUsername());
        }
        return blockList;
    }
    public LinkedList<String> showMuteList(String username){
        LinkedList<String> muteList = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : user.getMuted()){
            User targetUser = Load.loadUser(target);
            muteList.add(targetUser.getUsername());
        }
        return muteList;
    }
    public LinkedList<String> showReportList(String username){
        LinkedList<String> reportList = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : user.getReportedU()){
            User targetUser = Load.loadUser(target);
            reportList.add(targetUser.getUsername());
        }
        return reportList;
    }
    public LinkedList<String> showFollowings(String username){
        LinkedList<String> followings = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : user.getFollowings()){
            User targetUser = Load.loadUser(target);
            followings.add(targetUser.getUsername());
        }
        return followings;
    }

    public String accept(SendAcceptEvent e){
        String username = e.getUsername();
        String target = e.getTarget();
        String warn = e.getWarn();
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        user.getFollowers().add(targetUser.getUsername());
        targetUser.getFollowings().add(user.getUsername());
        user.getRequests().remove(targetUser.getUsername());
        logger.info(user.getUsername() + " " + "accepted" + " " + targetUser.getUsername());
        user.updateLastSeen();
        user.saveUser();
        targetUser.saveUser();
        warn = "user accepted";
        return warn;
    }
    public String rejectSilently(SendRejectSilentEvent e){
        String username = e.getUsername();
        String target = e.getTarget();
        String warn = e.getWarn();
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        user.getRequests().remove(targetUser.getUsername());
        logger.info(user.getUsername() + " " + "rejected" + " " + targetUser.getUsername());
        user.updateLastSeen();
        user.saveUser();
        warn = "user rejected";
        return warn;
    }
    public String reject_notify(SendRejectNotifyEvent e){
        String username = e.getUsername();
        String target = e.getTarget();
        String warn = e.getWarn();
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        user.getRequests().remove(targetUser.getUsername());
        targetUser.getRejectedFrom().add(user.getUsername());
        logger.info(user.getUsername() + " " + "rejected and notified" + " " + targetUser.getUsername());
        user.updateLastSeen();
        user.saveUser();
        targetUser.saveUser();
        warn = " nice =) ";
        return warn;
    }
    public LinkedList<String> showRejectedFrom(String username){
        LinkedList<String> rejected = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : user.getRejectedFrom()){
            User targetUser = Load.loadUser(target);
            rejected.add(targetUser.getUsername());
        }
        return rejected;
    }
    /////
    public void logOut(SendLogOutEvent e){
        LoginView loginView = e.getLoginView();
        loginView.loginPage();
    }

}
