package edu.najah.cap.deletion;

import edu.najah.cap.activity.UserActivityService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.payment.PaymentService;
import edu.najah.cap.posts.PostService;

public class PremiumUserDelete implements DeleteUserHardType{
    @Override
    public void deleteuser(UserProfile user) {

        UserActivityService userActivityService = new UserActivityService();
        PaymentService paymentService = new PaymentService();
        UserService userService = new UserService();
        PostService postService = new PostService();
        try {
            userActivityService.removeUserActivity(user.getUserName(), null);
            paymentService.removeTransaction(user.getUserName(), null);
            userService.deleteUser(user.getUserName());
            postService.deletePost(user.getUserName(), null);
        } catch (SystemBusyException | BadRequestException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
