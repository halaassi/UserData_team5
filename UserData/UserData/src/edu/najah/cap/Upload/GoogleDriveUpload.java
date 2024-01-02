package edu.najah.cap.Upload;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.activity.UserActivityService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.payment.PaymentService;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;
import edu.najah.cap.posts.PostService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class GoogleDriveUpload implements Upload {
  private final Logger logger = Logger.getLogger(getClass());
  private final IUserService userService = new UserService();
  private final IPayment paymentService = new PaymentService();
  private final IPostService postService = new PostService();
  private final IUserActivityService userActivityService = new UserActivityService();


  @Override
  public void uploadFile(String userId) throws SystemBusyException, BadRequestException, NotFoundException {
    try {
      List<ByteArrayOutputStream> pdfByteArrays = new ArrayList<>();

      List<Transaction> transactions = paymentService.getTransactions(userId);
      pdfByteArrays.add(ConvertToPDF.convertTransactionsToPDF(transactions));

      UserProfile userProfile = userService.getUser(userId);
      pdfByteArrays.add(ConvertToPDF.convertUserProfileToPDF(userProfile));

      List<Post> posts = postService.getPosts(userId);
      pdfByteArrays.add(ConvertToPDF.convertPostsToPDF(posts));

      List<UserActivity> userActivities = userActivityService.getUserActivity(userId);
      pdfByteArrays.add(ConvertToPDF.convertUserActivitiesToPDF(userActivities));

      String folderPath = "GoogleDrive/";
      String zipFilePath = folderPath + userId + "_ZipFile.zip";

      File folder = new File(folderPath);
      if (!folder.exists()) {
        folder.mkdirs();
      }

      ConvertToZip.createZipFile(zipFilePath, pdfByteArrays);

      System.out.println("File Uploaded to Google Drive: " + zipFilePath);
    } catch (SystemBusyException | BadRequestException | NotFoundException e) {

      throw e;
    } catch (Exception e) {

      logger.error("Error during file upload");
    }
  }

}


