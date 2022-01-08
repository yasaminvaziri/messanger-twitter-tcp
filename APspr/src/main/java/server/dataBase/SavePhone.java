package server.dataBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SavePhone {
    private static final Logger logger = LogManager.getLogger(SavePhone.class);
    public static void savePhone(String username){
        User user =  Load.loadUser(username);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().serializeNulls().create();
        String s = gson.toJson(user.getPhoneNumber());
        String path = "resources/savePhone";
        path +="/" + (user.getId());
        path += ".txt";
        File file = new File(path);
        try {
            if (!file.exists())
                file.createNewFile();
            else {
                file.delete();
                file.createNewFile();
                  logger.info("phone-file created for user");
            }
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            gson.toJson(user.getPhoneNumber(), printStream);
            printStream.close();
        }catch (IOException e){
            System.out.println("error");
              logger.info("could not create file for user");
        }

    }
}
