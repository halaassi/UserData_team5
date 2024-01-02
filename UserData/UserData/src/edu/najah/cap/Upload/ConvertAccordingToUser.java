package edu.najah.cap.Upload;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivityService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.iam.UserType;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.PaymentService;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.posts.PostService;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertAccordingToUser {
    private final Logger logger = Logger.getLogger(getClass());
    private final IUserService userService = new UserService();
    private final IPayment paymentService = new PaymentService();
    private final IPostService postService = new PostService();
    private final IUserActivityService userActivityService = new UserActivityService();
    List<ByteArrayOutputStream> pdfByteArrays = new ArrayList<>();
    public  List<ByteArrayOutputStream> convertAccordingToUser(UserType userType, String userId) {
        try {
            try {
                UserProfile userProfile = userService.getUser(userId);
                pdfByteArrays.add(ConvertToPDF.convertUserProfileToPDF(userProfile));
            }catch (SystemBusyException | BadRequestException | NotFoundException e) {
                throw e;
            }

            try {
                List<Post> posts = postService.getPosts(userId);
                pdfByteArrays.add(ConvertToPDF.convertPostsToPDF(posts));
            }catch (SystemBusyException | BadRequestException | NotFoundException e) {
                throw e;
            }

            if (userType == UserType.PREMIUM_USER) {
                try {
                    List<Transaction> transactions = paymentService.getTransactions(userId);
                    pdfByteArrays.add(ConvertToPDF.convertTransactionsToPDF(transactions));
                }catch (SystemBusyException | BadRequestException | NotFoundException e) {
                    throw e;
                }
                try {
                    List<UserActivity> userActivities = userActivityService.getUserActivity(userId);
                    pdfByteArrays.add(ConvertToPDF.convertUserActivitiesToPDF(userActivities));
                } catch (SystemBusyException | BadRequestException | NotFoundException e) {
                    throw e;
                }
            } else if (userType == UserType.REGULAR_USER) {

                try {
                    List<UserActivity> userActivities = userActivityService.getUserActivity(userId);
                    pdfByteArrays.add(ConvertToPDF.convertUserActivitiesToPDF(userActivities));
                } catch (SystemBusyException | BadRequestException | NotFoundException e) {
                    throw e;
                }
            }


        } catch (IOException | SystemBusyException | BadRequestException | NotFoundException e) {
            // Handle exceptions
            logger.error("Error during creating zip", e);
        }
        return pdfByteArrays;
    }
}
