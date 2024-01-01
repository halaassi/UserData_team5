package edu.najah.cap.write_the_delet_user;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUsername {
    private static final Logger logger = Logger.getLogger(FileWriterUsername.class);

    private FileWriterUsername(){

}
    public static void saveUsernameToFile(String username) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("deleted_usernames.txt"))) {
            writer.write(username + System.lineSeparator());
        } catch (IOException e) {
            logger.error("Error saving username to file: " + e.getMessage());
        }

}
}
