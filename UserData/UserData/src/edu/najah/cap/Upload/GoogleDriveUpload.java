package edu.najah.cap.Upload;

import edu.najah.cap.IsUserDelete.IsUserDeleted;
import edu.najah.cap.IsUserDelete.UserDeleted;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import org.apache.log4j.Logger;

public class GoogleDriveUpload implements Upload {
  private final Logger logger = Logger.getLogger(getClass());
  private final IUserService userService = new UserService();
  ConvertAccordingToUser converter = new ConvertAccordingToUser();


  @Override
  public void uploadFile(String userId) throws SystemBusyException, BadRequestException, NotFoundException {

    try {
      UserProfile user;
      user = userService.getUser(userId);
      List<ByteArrayOutputStream> pdfByteArrays;
      pdfByteArrays = converter.convertAccordingToUser(user.getUserType(), userId);


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
      logger.error("Error during file upload", e);
    }


  }

}


