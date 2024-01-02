package edu.najah.cap.deletion;
import edu.najah.cap.activity.UserActivityService;
import edu.najah.cap.payment.PaymentService;
import edu.najah.cap.posts.PostService;
import edu.najah.cap.exceptions.*;
import edu.najah.cap.iam.*;


public class PremiumUserDelete implements DeleteUserHardType{
    @Override
    public void deleteUser(UserProfile user) {

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
