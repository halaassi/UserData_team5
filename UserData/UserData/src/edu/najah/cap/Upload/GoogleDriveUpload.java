package edu.najah.cap.Upload;
import java.io.*;

public class GoogleDriveUpload implements Upload {
  private static final String APPLICATION_NAME = "UserData";
  ConvertToZip convertToZip = new ConvertToZip();

  @Override
  public void uploadFile(String userId, String sourceFilePath) {
    try {
      String folderPath = "GoogleDrive/";
      String zipFilePath = folderPath + userId + "_ZipFile.zip";

      File folder = new File(folderPath);
      if (!folder.exists()) {
      folder.mkdirs();
      }

      convertToZip.createZipFile(sourceFilePath, zipFilePath);


      System.out.println("File Uploaded to Google Drive: " + zipFilePath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

