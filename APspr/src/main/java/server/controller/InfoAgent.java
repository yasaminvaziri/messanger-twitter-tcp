package server.controller;

import client.controller.MainController;
import shared.events.settingsEvent.SendDeleteAccEvent;
import shared.events.settingsEvent.SendInActivateEvent;
import shared.events.settingsEvent.SendLastSeenEvent;
import shared.events.settingsEvent.SendPrivacyEvent;
import client.graphic.setting.LastSeenView;
import client.graphic.setting.PrivacyView;
import server.app.controller.RegisterAgent;
import server.dataBase.Load;
import server.dataBase.SaveEmail;
import server.dataBase.SavePhone;
import server.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class InfoAgent {
    private static final Logger logger = LogManager.getLogger(InfoAgent.class);

    public String showUsername(String username){
        User user = Load.loadUser(username);
        if (user != null) {
            user.updateLastSeen();
            return user.getUsername();

        }
        else
            return null;
    }

    public String showPass(String username){
        User user = Load.loadUser(username);
        if (user != null) {
            return user.getPassword();
        }
        return null;
    }

    public String showName(String username){
        User user = Load.loadUser(username);
        if (user != null){
            return user.getFullName();
        }
        return null;
    }

    public String showEmail(String username){
        User user = Load.loadUser(username);
        if (user != null) {
            if (user.isPermit() || user.getUsername().equals(MainController.username)) {
                user.updateLastSeen();
                return user.getEmail();
            }
            return null;
        }
        return null;
    }

    public String showPhone(String username){
        User user = Load.loadUser(username);
        if (user != null){
            if (user.isPermit() || user.getUsername().equals(MainController.username)){
                return user.getPhoneNumber();
            }
            return null;
        }
        return null;
    }

    public String showBirth(String username){
        User user = Load.loadUser(username);
        if (user != null){
            if (user.isPermit() || user.getUsername().equals(MainController.username)){
                return user.getBirthDay();
            }
            return null;
        }
        return null;
    }

    public String showBio(String username){
        User user = Load.loadUser(username);
        if (user != null){
            if (user.isPermit() || user.getUsername().equals(MainController.username)){
                return user.getBiography();
            }
            return null;
        }
        return null;
    }

    public String addBirthdate(String username, String date){
        User user = Load.loadUser(username);
        user.setBirthDay(date);
        user.saveUser();
        user.updateLastSeen();
        return date;
    }

    public String addBio(String username, String bio){
        User user = Load.loadUser(username);
        user.setBiography(bio);
        user.updateLastSeen();
        user.saveUser();
        return bio;
    }

    public String showLastSeen(String you,String target, String t){
        User user_you = Load.loadUser(you);
        User targetUser = Load.loadUser(target);
        if (targetUser != null) {
            if (targetUser.isPermit()) {
                if (targetUser.getShowLastSeen() == 0) {
                    t = "last seen recently";
                } else if (targetUser.getFollowings().contains(user_you.getUsername()) &&
                        !targetUser.getBlockList().contains(user_you.getUsername())) {

                    t = targetUser.getLastTime().getHour() + " " + targetUser.getLastTime().getMinute() + " " +
                            targetUser.getLastTime().getSecond();
                } else {
                    t = targetUser.getLastTime().getHour() + " " + targetUser.getLastTime().getMinute() + " " +
                            targetUser.getLastTime().getSecond();
                }
                return t;
            }
            return null;
        }
        return null;
    }
    public String changeFullName(String username, String f2, String t){
        User user = Load.loadUser(username);
        if (RegisterAgent.validName(f2)) {
            user.setFullName(f2);
            user.updateLastSeen();
            user.saveUser();
            t = "successfully changed";
             logger.info(user.getUsername() + "changed fullName");
        }
        else
            t = "invalid! try again";
        logger.error(user.getUsername() + "entered an invalid name");
        return t;
    }

    public String changePassword(String username, String p2, String t){
        User user = Load.loadUser(username);
        if (RegisterAgent.isValidPassword(p2)) {
            user.setPassword(p2);
            user.updateLastSeen();
            user.saveUser();
            t = "successfully changed";
            logger.info(user.getUsername() + "changed password");
        }
        else
            t = "invalid! try again";
        logger.error(user.getUsername() + " " + "entered an invalid password");
        return t;
    }

    public String changePhoneNumber(String username, String pn2, String t) {
        User user = Load.loadUser(username);
        if (RegisterAgent.isValidPhoneNumber(pn2)) {
            user.setPhoneNumber(pn2);
            SavePhone.savePhone(username);
            user.updateLastSeen();
            user.saveUser();
            t = "successfully changed";
              logger.info(user.getUsername() + "phoneNumber changed");
        }
        else
            t = "invalid! try again";
        logger.error(user.getUsername() + " " + "entered an invalid phone number");
        return t;
    }

    public String changeEmail(String username,String e2, String t) {
        User user = Load.loadUser(username);

        if (RegisterAgent.isValidEmail(e2)) {
            user.setEmail( e2);
            user.updateLastSeen();
            user.saveUser();
            SaveEmail.saveEmail(username);
            t = "successfully changed!";
             logger.info(user.getUsername() + "email changed");
        } else
            t = "invalid! try again";
        logger.error(user.getUsername() + " " + "entered an invalid email");

        return t;
    }
    public String switchPrivacy(String username, String warn){
        User user = Load.loadUser(username);
        if (user.isPublic() == true){
            user.setPublic(false);
            user.setShowLastSeen(1);
            user.updateLastSeen();
            user.saveUser();
            warn = "account switched to private";
            logger.info(user.getUsername() + " " + " privacy changed");
        }
        else if (!user.isPublic()){
            user.setPublic(true);
            user.setShowLastSeen(2);
            user.updateLastSeen();
            user.saveUser();
            warn = "account switched to public";
            logger.info(user.getUsername() + " " + "privacy changed");
        }
        return warn;
    }
    public String changePermission(String username, String warn){
        User user = Load.loadUser(username);
        if (user.isPermit() == true){
            user.setPermit(false);
            user.updateLastSeen();
            user.saveUser();
            warn = "your full name and username is visible";
        }
        else if (!user.isPermit()){
            user.setPermit(true);
            user.updateLastSeen();
            user.saveUser();
            warn = "your info is visible";
        }
        return warn;
    }
    public String lastSeenEveryone(String username, String warn){
        User user = Load.loadUser(username);
        if (user.getShowLastSeen() == 0 || user.getShowLastSeen() == 1){
            user.setShowLastSeen(2);
            user.updateLastSeen();
            user.saveUser();
            warn = "last seen = everyone";
        }
        return warn;
    }
    public String lastSeenFollowings(String username, String warn){
        User user = Load.loadUser(username);
        if (user.getShowLastSeen() == 0 || user.getShowLastSeen() == 2){
            user.setShowLastSeen(1);
            user.updateLastSeen();
            user.saveUser();
            warn = "last seen = followings only";
        }
        return warn;
    }
    public String lastSeenNobody(String username, String warn){
        User user = Load.loadUser(username);
        if (user.getShowLastSeen() == 2 || user.getShowLastSeen() == 1){
            user.setShowLastSeen(0);
            user.updateLastSeen();
            user.saveUser();
            warn = "last seen = nobody";
        }
        return warn;
    }
    public void deleteYourAccount(SendDeleteAccEvent event){
        String username = event.getUsername();
        User user = Load.loadUser(username);
        if (user != null) {
            try {
                String path = "resources/saveUser";
                path += "/" + user.getId();
                path += ".txt";
                File file = new File(path);
                if (file.exists()) {
                    System.out.println("1");
                }
                if (file.delete()) {
                    System.out.println(file.getName() + "deleted");
                    logger.info(user.getUsername() + " deleted file ");
                    System.exit(0);
                } else {
                    System.out.println("failed");
                     logger.fatal(" could not delete file");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println("user does not exist");
    }

    public void inactivate(SendInActivateEvent e) {
        String username = e.getUsername();
        User user = Load.loadUser(username);
        user.updateLastTime();
        if (user.isActive()) {
            user.setActive(false);
            user.saveUser();
             logger.info(user.getUsername() + "deactivated");
        } else
            return;
    }
    public void goToPrivacy(SendPrivacyEvent privacyEvent){
        PrivacyView privacyView = privacyEvent.getPrivacyView();
        privacyView.privacyPage();
    }
    public void goToLastSeen(SendLastSeenEvent e){
        LastSeenView lastSeenView = e.getSeenView();
        lastSeenView.lastSeenPage();
    }

}
