package client.controller;

import client.graphic.activity.RejectedFromView;
import client.holders.TokenHolder;
import client.stream.StreamUtil;
import shared.GsonUtil;
import shared.lists.RejectedFrom;
import shared.networking.Request;
import shared.networking.Response;

import javax.swing.*;
import java.io.FileReader;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class RejectedFromController {
    Response response;
    boolean end = false;
    public RejectedFromController() {
        getScores();
    }

    public void getScores() {
        if (end)
            return;
        Thread thread = new Thread(()->{
            try {
                sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            try(FileReader reader = new FileReader("src/main/java/client/config/controllers.properties")) {
                Properties properties = new Properties();
                properties.load(reader);
                StreamUtil.sendRequest(new Request(properties.getProperty("rejectedFrom"), "", TokenHolder.token));

            }catch (Exception e ){
                e.printStackTrace();
            }
            //StreamUtil.sendRequest(new Request("scores", "", TokenHolder.token));
            response = StreamUtil.getResponse();
            SwingUtilities.invokeLater(()->{
               // if (!end)
                   // new RejectedFromView(GsonUtil.getGson().fromJson(response.data, RejectedFrom.class), this);
            });
            getScores();
        });
        thread.start();
    }
}
