package client.graphic.photo;

import server.controller.FileImage;
import client.controller.MainController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Photo {
    private static ImageIcon ii;
    public static BufferedImage scaleImage(int w, int h, BufferedImage img) {
        BufferedImage bi;
        bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        try {
            FileReader reader = new FileReader("resources/configuration/photo.properties");
            Properties properties = new Properties();
            properties.load(reader);
            g2d.drawImage(img, Integer.parseInt(properties.getProperty("g2dX")),
                    Integer.parseInt(properties.getProperty("g2dY")), w, h, null);
        }catch (Exception e){
            e.printStackTrace();
        }
        g2d.dispose();
        return bi;
    }
    public static void addPhoto()  {
        JLabel photo = new JLabel();
        JButton setPhoto = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/photo.properties");
            Properties properties = new Properties();
            properties.load(reader);
            photo.setText(properties.getProperty("photo"));
            photo.setIcon(scaleFileImage(FileImage.loadImage(MainController.username)));
            photo.setBounds(Integer.parseInt(properties.getProperty("photoX")),
                    Integer.parseInt(properties.getProperty("photoY")),
                    Integer.parseInt(properties.getProperty("photoWidth")),
                    Integer.parseInt(properties.getProperty("photoHeight")));
            setPhoto.setText(properties.getProperty("setPhoto"));
            setPhoto.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            setPhoto.setBounds(Integer.parseInt(properties.getProperty("setX")),
                    Integer.parseInt(properties.getProperty("setY")),
                    Integer.parseInt(properties.getProperty("setWidth")),
                    Integer.parseInt(properties.getProperty("setHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        setPhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                final File f = chooser.getSelectedFile();
                if (f == null)
                    return;
                try {
                    photo.setIcon(scaleFileImage(f));
                    FileImage.saveImage(f,MainController.username);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

        });

        MainController.panel.add(setPhoto);
        MainController.panel.add(photo);

    }
    public static ImageIcon scaleFileImage(File f) {
        FileReader reader;
        try {
            reader = new FileReader("resources/configuration/photo.properties");
            Properties properties = new Properties();
            properties.load(reader);
            ii = new ImageIcon(scaleImage(Integer.parseInt(properties.getProperty("w")),
                    Integer.parseInt(properties.getProperty("h")), ImageIO.read(new File(f.getAbsolutePath()))));
            return ii;
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        return null;
    }




}
