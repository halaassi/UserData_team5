package edu.najah.cap.deletion;
import edu.najah.cap.exceptions.*;
import edu.najah.cap.iam.*;
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
