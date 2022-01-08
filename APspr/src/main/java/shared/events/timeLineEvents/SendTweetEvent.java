package shared.events.timeLineEvents;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class  SendTweetEvent {
    private String username, content;
    private byte[] image;

    public SendTweetEvent(String username, String content, byte[] image) {
        this.username = username;
        this.content = content;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

       private static ImageIcon convertToFxImage(BufferedImage image) {
//        ImageWriter wr = null;
//        if (image != null) {
//            wr = new WritableImage(image.getWidth(), image.getHeight());
//            PixelWriter pw = wr.getPixelWriter();
//            for (int x = 0; x < image.getWidth(); x++) {
//                for (int y = 0; y < image.getHeight(); y++) {
//                    pw.setArgb(x, y, image.getRGB(x, y));
//                }
//            }
//        }
//
//        return new ImageView(wr).getImage();
           return null;
    }

    public static ImageIcon load(byte[] photo) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(photo);
        try {
            BufferedImage image = ImageIO.read(inputStream);
            return convertToFxImage(image);
        } catch (IOException ignored) {}
        return null;
    }
    public static byte[] imageFileToByte(File file) {
        if (file == null)
            return null;
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}

