package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.app.Auth;
import server.app.Profile;
import server.app.Tweets;
import server.controller.MessageAgent;
import server.stream.StreamUtil;
import shared.networking.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

public class ClientThread extends Thread {
    private static final Logger logger = LogManager.getLogger(ClientThread.class);
    Socket socket;

    public ClientThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        Auth auth = new Auth();
        Tweets tweets = new Tweets();
        Profile profile = new Profile();
        ////other classes

        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
           logger.error("error in getting inputStream");
        }
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            logger.error("error in getting outputStream");
        }
        while (true) {
            Request request = null;
            try (FileReader reader = new FileReader("src/main/java/server/config/client.properties")) {
                Properties properties = new Properties();
                properties.load(reader);
                try {
                    request = StreamUtil.getRequest(dataInputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(properties.getProperty("er"));
                }
                if (request.type.equals(properties.getProperty("register"))) {
                    auth.register(request, dataOutputStream);
                } else if (request.type.equals(properties.getProperty("login"))) {
                    auth.login(request, dataOutputStream);
                }else if(request.type.equals(properties.getProperty("tweet"))){
                    tweets.sendTweet(request,dataOutputStream);
                }else if (request.type.equals(properties.getProperty("like"))){
                    tweets.likeTweet(request,dataOutputStream);
                }else if(request.type.equals(properties.getProperty("dislike"))){
                    tweets.dislikeTweet(request,dataOutputStream);
                }
                else if (request.type.equals(properties.getProperty("pro"))){
                    profile.getProfile(request,dataOutputStream);
                }else if (request.type.equals(properties.getProperty("rejected_from"))){
                    profile.getRejectedList(request,dataOutputStream);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

}
