package edu.najah.cap.display;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.iam.*;
import org.apache.log4j.Logger;


public class Display implements displayUser{
    UserService userService = new UserService();
    private final Logger logger = Logger.getLogger(getClass());

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
            logger.info("User does not exist") ;
        }
    }
        private void displayUserDetails(UserProfile user) {
            System.out.println("User Data:");
            System.out.println("Username: " + user.getUserName());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
        }
}
