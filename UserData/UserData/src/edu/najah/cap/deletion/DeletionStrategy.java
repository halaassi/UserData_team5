package edu.najah.cap.deletion;
import edu.najah.cap.iam.UserProfile;

public interface DeletionStrategy {
    void delete (UserProfile user , String userType) ;
}

