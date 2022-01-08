package server.app;

import server.dataBase.Load;
import server.models.User;
import server.stream.StreamUtil;
import shared.GsonUtil;
import shared.UserInfo;
import shared.UserLists;
import shared.lists.RejectedFrom;
import shared.networking.Request;
import shared.networking.Response;

import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Profile {
    public void getProfile(Request request, DataOutputStream dataOutputStream) {
        try(FileReader reader = new FileReader("src/main/java/server/config/pro.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

            if (Tokens.tokenMap.getOrDefault(request.token, null) == null) {
                try {
                    StreamUtil.sendResponse(new Response(properties.getProperty("invalid_token"), properties.getProperty("denied")), dataOutputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            User user = Tokens.tokenMap.get(request.token);

            try {
                StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(new UserInfo(user)), properties.getProperty("accepted")),
                        dataOutputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getRejectedList(Request request, DataOutputStream dataOutputStream) {
        try(FileReader reader = new FileReader("src/main/java/server/config/pro.properties")) {
            Properties properties = new Properties();
            properties.load(reader);

//            try {
//                StreamUtil.sendResponse(new Response(GsonUtil.getGson().toJson(new RejectedFrom()), properties.getProperty("accepted")),
//                        dataOutputStream);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

