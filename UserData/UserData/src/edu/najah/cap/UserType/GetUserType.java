package edu.najah.cap.UserType;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.iam.UserType;

public class GetUserType {
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
                    throw new RuntimeException("Error getting user type for user: " + userName, e);
                }
               try {
                    Thread.sleep(1000 * (i + 1)); // Wait time increases with each retry
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } catch (BadRequestException | NotFoundException e) {
                throw new RuntimeException(e);
            }
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