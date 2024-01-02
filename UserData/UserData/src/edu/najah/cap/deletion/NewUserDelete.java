package edu.najah.cap.deletion;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.posts.PostService;


public class NewUserDelete implements DeleteUserHardType {

    @Override
    public void deleteuser(UserProfile user) {
        UserService userService = new UserService();
        PostService postService = new PostService();
        try {
            userService.deleteUser(user.getUserName());
            postService.deletePost(user.getUserName(), null);
        } catch (SystemBusyException | BadRequestException | NotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
