package edu.najah.cap.IsUserDelete;
import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserDeleted implements IsUserDeleted {
    private final Logger logger = Logger.getLogger(getClass());

    public boolean isUserDeleted(String userName) {

        try (BufferedReader reader = new BufferedReader(new FileReader("deleted_usernames.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equalsIgnoreCase(userName.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            logger.info("Error reading deleted usernames file: " + e.getMessage());
        }
        return false;
    }
}
