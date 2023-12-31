//save username in txt file before delete
//  all user info related to the username in the four packages
// Without modifying the existing packages

package edu.najah.cap.deletion;
import edu.najah.cap.iam.UserProfile;
import org.apache.log4j.Logger;


public class HardDeleteStrategy implements DeletionStrategy {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(HardDeleteStrategy.class);
    @Override
    public void delete(UserProfile user , String userType) {

            DeleteUserHardType deleteUserHardType;

            if ("NEW_USER".equals(userType)) {
                deleteUserHardType = new NewUserDelete();
            } else if ("REGULAR_USER".equals(userType)) {
                deleteUserHardType = new RegularUserDelete();
            } else if ("PREMIUM_USER".equals(userType)) {
                deleteUserHardType = new PremiumUserDelete();
            } else {
                throw new IllegalArgumentException("Invalid userType: " + userType);
            }

            deleteUserHardType.deleteuser(user);
            logger.info("hard delete completed for user: " + user.getUserName());
            FileWriterUsername.saveUsernameToFile(user.getUserName());


    }
}








