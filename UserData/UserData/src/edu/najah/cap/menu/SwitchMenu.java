package edu.najah.cap.menu;

import edu.najah.cap.IsUserDelete.IsUserDeleted;
import edu.najah.cap.IsUserDelete.UserDeleted;
import edu.najah.cap.Upload.DropboxUpload;
import edu.najah.cap.Upload.GoogleDriveUpload;
import edu.najah.cap.Upload.Upload;
import edu.najah.cap.Export.*;
import edu.najah.cap.UserType.*;
import edu.najah.cap.adduser.*;
import edu.najah.cap.deletion.*;
import edu.najah.cap.display.*;
import edu.najah.cap.exceptions.*;
import edu.najah.cap.iam.*;
import org.apache.log4j.Logger;
import java.util.Scanner;
import static edu.najah.cap.data.Application.getLoginUserName;

public class SwitchMenu implements Menu {
    private final Logger logger = Logger.getLogger(getClass());

    private static final int MAX_RETRIES = 5;

    private static final long RETRY_DELAY = 500;

    IsUserDeleted isUserDeleted =new UserDeleted();




    @Override
    public boolean menu(int index) {
        Scanner scanner = new Scanner(System.in);
        IUserService userService = new UserService();
        UserProfile userProfile = new UserProfile();
        displayUser displayUser = new Display();
        UserType userType = userProfile.getUserType();
        int retryCount = 0;

        switch (index) {
            case 1:
                try {
                    IsUserDeleted userDeleted = new UserDeleted();
                    if (userDeleted.isUserDeleted(getLoginUserName())) {
                        logger.info("Cannot export data for deleted user : "+getLoginUserName());
                    } else {
                        ExportService exportService = new ExportService(new ExporterFactory());
                        IUserType userTypeToExport = GetUserType.getUserType(getLoginUserName());
                        System.out.println(userTypeToExport);
                        userTypeToExport.exportUserData(exportService, getLoginUserName());
                        logger.info("Exporting data completed for user : "+getLoginUserName());
                    }
                } catch (SystemBusyException | BadRequestException | NotFoundException e) {
                    throw new RuntimeException(e);
                }
                return true;



            case 2:

                Upload upload;
                if(!isUserDeleted.isUserDeleted(getLoginUserName())) {
                    logger.info("If you want to upload to Google Drive Click 1 and if you want to upload to Dropbox Click 2");
                    int choice = scanner.nextInt();
                    while (retryCount < MAX_RETRIES) {
                        try {

                            if (choice == 1) {

                                upload = new GoogleDriveUpload();
                                upload.uploadFile(getLoginUserName());

                            } else if (choice == 2) {

                                upload = new DropboxUpload();
                                upload.uploadFile(getLoginUserName());


                            } else {
                                logger.info("The selected choice is wrong");
                            }
                            return true;

                        } catch (Exception e) {
                            logger.error("Error while hard uploading data. Retrying...");
                            retryCount++;
                            try {
                                Thread.sleep(RETRY_DELAY);
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }

                        }
                    }
                }
                else{
                    logger.info("This user Is Deleted");
                }


                return true;
            case 3:

                System.out.println("If you want to Soft delete Click 1 and if you want to hard delete Click 2 ");
                double num1 = Double.parseDouble(scanner.nextLine());
                DeletionStrategy deletionStrategy;


                while (retryCount < MAX_RETRIES) {

                    try {
                        if (num1 == 1) {

                            UserProfile userToDelete = userService.getUser(getLoginUserName());
                            deletionStrategy = new SoftDelete();
                            deletionStrategy.delete(userToDelete, String.valueOf(userType));
                        } else if (num1 == 2) {
                            UserProfile userToDelete = userService.getUser(getLoginUserName());
                            deletionStrategy = new HardDeleteStrategy();
                            deletionStrategy.delete(userToDelete, String.valueOf(userType));
                        }
                        else{
                            logger.error("The selected choice is wrong");
                        }
                        return true;
                    } catch (Exception e) {
                        logger.error("Error while hard deleting user. Retrying...");
                        retryCount++;
                        try {
                            Thread.sleep(RETRY_DELAY);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                return true;
            case 4:
                displayUser.displayUserData(getLoginUserName());
                return true;
            case 5:
                System.out.println("Add user name ");
                String userName = scanner.nextLine();
                UsarAddFromUser adduser;
                adduser = new AddUserfromUser();
                adduser.add(userName);
                return true;
            case 6:
                System.out.println("Exit the program. Goodbye! Happy New Year :)");

                System.exit(0);


                return false;
            default:
                System.out.println("Invalid option. Please choose again.");
        }
        return true;
    }
}