package util;

import client.controller.MainController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.mainController();
        logger.info("program started");
    }
}
