package edu.najah.cap.deletion;
import edu.najah.cap.data.Application;
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
            logger.info("Error saving username to file: " + e.getMessage());
        }

}
}
