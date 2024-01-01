package edu.najah.cap.adduser;
import edu.najah.cap.is_user_drlete.IsUserDeleted;
import edu.najah.cap.is_user_drlete.UserDeleted;
import edu.najah.cap.iam.UserService;
import org.apache.log4j.Logger;

import edu.najah.cap.iam.UserProfile;

public class adduserfromuser implements UsarAddFromUser {
    private static final Logger logger = Logger.getLogger(adduserfromuser.class);
    UserService userService = new UserService();

    @Override
    public void add(String user) {
        IsUserDeleted userDeleted;
       userDeleted = new UserDeleted();
        userDeleted.isUserDeleted(user);
        UserProfile newUser = new UserProfile();
        if (userDeleted.isUserDeleted(user) == true) {
            System.out.println("the user has already is deleted .");}
        else {newUser.setUserName(user);
            userService.addUser(newUser);
            System.out.println("the user add .");}

        
    }
}
