package client.controller;

import shared.events.welcomeEvents.SendRegisterEvent;
import client.graphic.welcome.RegisterView;
import client.stream.StreamUtil;
import shared.GsonUtil;
import shared.networking.AuthRequest;
import shared.networking.Request;
import shared.networking.Response;

import java.io.FileReader;
import java.util.Properties;

public class RegisterController {
    RegisterView registerView;
    public RegisterController(RegisterView registerView){
        this.registerView = registerView;
    }
    public void registerPressed(String username, String name, String password, String email, String phone){
        AuthRequest authRequest = new AuthRequest(username,password,name,email,phone,null,null);
        try (FileReader reader = new FileReader("src/main/java/client/config/server.controller.properties")){
            Properties properties = new Properties();
            properties.load(reader);
            StreamUtil.sendRequest(new Request(properties.getProperty("register"), GsonUtil.getGson().toJson(authRequest)));
            Response response = StreamUtil.getResponse();
            if (response.status.equals(properties.getProperty("created"))) {
                SendRegisterEvent registerEvent = new SendRegisterEvent(username,password,name,email,phone);
                registerView.registerListener.register(registerEvent);

//                MenuView menuView = new MenuView();
//                menuView.menuPage();
            }
            else {
                registerView.warnLabel.setText(response.data);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        }



    }



