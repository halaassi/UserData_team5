package edu.najah.cap.deletion;

import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.write_the_delet_user.FileWriterUsername;
import org.apache.log4j.Logger;

public class HardDeleteStrategy implements DeletionStrategy {
    private static final Logger logger = Logger.getLogger(HardDeleteStrategy.class);

    private static final int MAX_RETRIES = 3;

    private static final long RETRY_DELAY = 500;

    @Override
    public void delete(UserProfile user, String userType) {
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

        int retryCount = 0;

        while (retryCount < MAX_RETRIES) {
            try {
                deleteUserHardType.deleteuser(user);
                logger.info("Hard delete completed for user: " + user.getUserName());
                FileWriterUsername.saveUsernameToFile(user.getUserName());
                break;
            } catch (Exception e) {
                logger.error("Error while hard deleting user. Retrying...", e);
                retryCount++;
                try {
                    Thread.sleep(RETRY_DELAY);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
