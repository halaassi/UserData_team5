package edu.najah.cap.Upload;
import java.io.*;

public class DropboxUpload implements Upload{
    private static final String APPLICATION_NAME = "UserData";
    ConvertToZip convertToZip = new ConvertToZip();
    public void uploadFile(String userId, String sourceFilePath) {
        try {
            String folderPath = "Dropbox/";
            String zipFilePath = folderPath + userId + "_ZipFile.zip";

            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            convertToZip.createZipFile(sourceFilePath, zipFilePath);


            System.out.println("File Uploaded to Dropbox: " + zipFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
