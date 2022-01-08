package shared;

import server.models.User;

public class UserInfo {
    private String username;
    private String password;
    private String name;
    private String phone;
    private String bio;
    private String email;
    private String birth;

    public UserInfo(User user){
        username = user.getUsername();
        password = user.getPassword();
        name = user.getFullName();
        phone = user.getPhoneNumber();
        bio = user.getBiography();
        email = user.getEmail();
        birth = user.getBirthDay();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
