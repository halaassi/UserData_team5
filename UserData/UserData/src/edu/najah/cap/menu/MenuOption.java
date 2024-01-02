package edu.najah.cap.menu;

import org.apache.log4j.Logger;

public class MenuOption {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(MenuOption.class);

    public void display(){
        logger.info("What do you want to do");
        logger.info(
                "\n1-Exporting Data\n" +
                        "2-Upload Data\n" +
                        "3-Delete Data\n" +
                        "4-display user Data\n" +
                        "5-add user\n" +
                        "6-exit"
        );

    }
}
