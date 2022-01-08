package server.app;

import server.app.controller.RegisterAgent;
import server.dataBase.Load;
import server.models.User;
import server.stream.StreamUtil;
import shared.GsonUtil;
import shared.networking.AuthRequest;
import shared.networking.Request;
import shared.networking.Response;

import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Auth {
    public void register(Request request, DataOutputStream dataOutputStream){
        AuthRequest authRequest = GsonUtil.getGson().fromJson(request.data, AuthRequest.class);
        try(FileReader reader = new FileReader("src/main/java/server/config/auth.properties")){
            Properties properties = new Properties();
            properties.load(reader);
            if (authRequest.getUsername().equals("") || authRequest.getName().equals("") ||
            authRequest.getEmail().equals("") || authRequest.getPassword().equals("") ||
            authRequest.getPhone().equals("")){
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("empty"),properties.getProperty("id"))
                    ,dataOutputStream);

                }catch (IOException ioException){
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            else if (Load.UserExists(authRequest.getUsername())){
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("tu"),properties.getProperty("id")),
                            dataOutputStream);
                }catch (IOException ioException){
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            else if (!RegisterAgent.validUsername(authRequest.getUsername())){
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("iu"),properties.getProperty("id")),
                            dataOutputStream);
                }catch (IOException ioException){
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            else if (!RegisterAgent.validName(authRequest.getName())){
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("in"),properties.getProperty("id")),
                            dataOutputStream);
                }catch (IOException ioException){
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            else if (!RegisterAgent.isValidEmail(authRequest.getEmail())){
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("ie"),properties.getProperty("id")),
                            dataOutputStream);
                }catch (IOException ioException){
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            else if (!RegisterAgent.isValidPassword(authRequest.getPassword())){
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("ip"),properties.getProperty("id")),
                            dataOutputStream);
                }catch (IOException ioException){
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            else if (!RegisterAgent.isValidPhoneNumber(authRequest.getPhone())){
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("iph"),properties.getProperty("id")),
                            dataOutputStream);
                }catch (IOException ioException){
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            User user = new User(authRequest.getUsername(), authRequest.getEmail(),authRequest.getPassword()
            ,authRequest.getName(),authRequest.getBirthday(),authRequest.getPhone(),authRequest.getBio());
            user.saveUser();
            try {
                StreamUtil.sendResponse(new Response("", properties.getProperty("created")),
                        dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println(properties.getProperty("csr"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void login(Request request, DataOutputStream dataOutputStream){
        AuthRequest authRequest = GsonUtil.getGson().fromJson(request.data, AuthRequest.class);
        try(FileReader reader = new FileReader("src/main/java/server/config/auth.properties")){
            Properties properties = new Properties();
            properties.load(reader);
            if (!Load.UserExists(authRequest.getUsername())) {
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("ud"), "Invalid_data"), dataOutputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            User user = Load.loadUser(authRequest.getUsername());
            if (!user.getPassword().equals(authRequest.getPassword())) {
                try {
                    StreamUtil.sendResponse(new Response("incorrect password", "Invalid_data"), dataOutputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println(properties.getProperty("csr"));
                }
                return;
            }
            String token = Tokens.generateToken(user);
            try {
                StreamUtil.sendResponse(new Response(token, "accepted"), dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println(properties.getProperty("csr"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
