package client.controller;

import client.graphic.home.InfoView;
import client.holders.TokenHolder;
import client.stream.StreamUtil;
import shared.GsonUtil;
import shared.UserInfo;
import shared.networking.Request;
import shared.networking.Response;

import javax.swing.*;
import java.io.FileReader;
import java.util.Properties;

public class ProfileController {
    Response response;
    public ProfileController(){
        try(FileReader reader = new FileReader("src/main/java/client/config/controller.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            Thread thread = new Thread(()->{
                StreamUtil.sendRequest(new Request(properties.getProperty("profile"), "" , TokenHolder.token));
                response = StreamUtil.getResponse();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        InfoView infoView = new InfoView();
                        infoView.infoPage(GsonUtil.getGson().fromJson(response.data, UserInfo.class));
                        //new InfoView(GsonUtil.getGson().fromJson(response.data, UserInfo.class));
                    }
                });
            });
            thread.start();

        }catch (Exception e ){
            e.printStackTrace();
        }


    }
}
