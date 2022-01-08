package server.controller;



import java.io.File;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileImage {


    public static void saveImage(File file,String username){
        try {
            File f = new File("./resources/saveProfile/" + username);
            Files.copy(file.toPath(),f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public static File loadImage(String username){
        File f = new File("./resources/saveProfile/" + username);
        if (f.exists())
            return f;
        return new File("./resources/saveProfile/" + "no-photo.jpg");
    }

    public static void saveTweetPhoto(File file, int id){
        try {
            File f = new File("./resources/saveTweetPhotos/" + id);
            Files.copy(file.toPath(),f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public static File loadTweetPhoto(String username, int id){
        File f = new File("./resources/saveTweetPhotos/" + id);
        if (f.exists())
            return f;
        return null;
    }

    public static void saveMessagePhoto(File file, int id){
        try {
            File f = new File("./resources/saveMessagePhotos/" + id);
            Files.copy(file.toPath(),f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public static File loadMessagePhoto(String username, int id){
        File f = new File("./resources/saveMessagePhotos/" + id);
        if (f.exists())
            return f;
        return null;
    }


}