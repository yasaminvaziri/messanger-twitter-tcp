package shared.events.welcomeEvents;

public class SendRegisterEvent {
   private String userText, passText, name, email, phone, warn;

   public SendRegisterEvent(String username, String password, String fullName, String email, String phone){
       this.userText = username;
       this.passText = password;
       this.name = fullName;
       this.email = email;
       //this.warn = warnLabel;
       this.phone = phone;
   }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }

    public String getPassText() {
        return passText;
    }

    public void setPassText(String passText) {
        this.passText = passText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }
}
