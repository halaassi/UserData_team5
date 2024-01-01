package edu.najah.cap.deletion;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.write_the_delet_user.FileWriterUsername;
import org.apache.log4j.Logger;

public class HardDeleteStrategy implements DeletionStrategy
{
    private static final Logger logger = Logger.getLogger(HardDeleteStrategy.class);
    @Override
    public void delete(UserProfile user, String userType)
    {
        DeleteUserHardType deleteUserHardType;
        if ("NEW_USER".equals(userType))
        {
            deleteUserHardType = new NewUserDelete();
        }
        else if ("REGULAR_USER".equals(userType))
        {
            deleteUserHardType = new RegularUserDelete();
        }
        else if ("PREMIUM_USER".equals(userType))
        {
            deleteUserHardType = new PremiumUserDelete();
        }
        else
        {
            throw new IllegalArgumentException("Invalid userType: " + userType);
        }
        deleteUserHardType.deleteuser(user);
        logger.info("Hard delete completed for user: " + user.getUserName());
        FileWriterUsername.saveUsernameToFile(user.getUserName());

    }
}
