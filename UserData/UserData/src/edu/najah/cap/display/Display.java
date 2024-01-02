package edu.najah.cap.display;

import edu.najah.cap.data.Application;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;
import org.apache.log4j.Logger;

public class Display implements displayUser{
    UserService userService = new UserService();
    private static final Logger logger = Logger.getLogger(Application.class);


    @Override
    public void displayUserData(String userName) {
        try {

            UserProfile user = userService.getUser(userName);

            if (user != null) {
                displayUserDetails(user);
            } else {
                throw new NotFoundException(" User does not exist");
            }
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }
        private void displayUserDetails(UserProfile user) {
            logger.info("User Data:");
            logger.info("Username: " + user.getUserName());
            logger.info("First Name: " + user.getFirstName());
            logger.info("Last Name: " + user.getLastName());
            logger.info("Email: " + user.getEmail());
        }
}
