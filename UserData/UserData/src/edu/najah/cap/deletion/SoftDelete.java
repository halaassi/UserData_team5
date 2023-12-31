package edu.najah.cap.deletion;
import edu.najah.cap.iam.UserProfile;
import org.apache.log4j.Logger;

public class SoftDelete implements DeletionStrategy {
    private static final Logger logger = Logger.getLogger(SoftDelete.class);

    @Override
    public void delete(UserProfile user,String userType) {
        try {
            // Soft delete: Users can clear everything except basic information such as username and email.
            user.setPhoneNumber(null);
            user.setPassword(null);
            user.setRole(null);
            user.setDepartment(null);
            user.setOrganization(null);
            logger.info("Soft delete completed for user: " + user.getUserName());
        } catch (Exception e) {
            logger.info("Error during soft delete: " + e.getMessage());
        }
    }
}
