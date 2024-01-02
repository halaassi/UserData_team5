package edu.najah.cap.UserType;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.iam.UserType;
import org.apache.log4j.Logger;

public class GetUserType {
    private static final Logger logger = Logger.getLogger(GetUserType.class);

    public static IUserType getUserType(String userName) {
        UserProfile userProfile = new UserProfile();
        IUserService userService = new UserService();
        int maxRetries = 3;
        for (int i = 0; i < maxRetries; i++) {
            try {
                userProfile = userService.getUser(userName);
                break;
            } catch (SystemBusyException e) {
                if (i == maxRetries - 1) {
                    logger.error("Error getting user type for user: " + userName, e);
                }
               try {
                    Thread.sleep(1000 * (i + 1));
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } catch (BadRequestException | NotFoundException e) {
                logger.error(" This User is not Exist : " + userName);            }
        }
        UserType userType = userProfile.getUserType();
        switch (userType) {
            case NEW_USER:
                return new NewIUser();
            case REGULAR_USER:
                return new RegularIUser();
            case PREMIUM_USER:
                return new PremiumIUser();
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}