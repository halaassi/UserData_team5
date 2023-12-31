package edu.najah.cap.menu;


import edu.najah.cap.deletion.HardDeleteStrategy;
import org.apache.log4j.Logger;

public class MenuOption {


    private static final org.apache.log4j.Logger logger = Logger.getLogger(MenuOption.class);

    public void diplay(){
        logger.info("what do you want to do");
        logger.info("""
                     
                     1-Exporting Data
                     2-Upload Data
                     3-Delete Data
                     4-display user Data
                     5-add user
                     6-exit"""
        );
    }


}
