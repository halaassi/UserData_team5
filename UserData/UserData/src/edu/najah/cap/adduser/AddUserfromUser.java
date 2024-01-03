package edu.najah.cap.adduser;
import edu.najah.cap.IsUserDelete.*;
import edu.najah.cap.iam.*;


public class AddUserfromUser implements UsarAddFromUser {
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
