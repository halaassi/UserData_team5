package edu.najah.cap.Export;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.activity.UserActivityService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserActivityExporter implements Exporter {
    private final Logger logger = Logger.getLogger(getClass());

    private String formatUserActivity(UserActivity activity) {
        return "Activity: " + activity.getActivityType() + ", Time: " + activity.getActivityDate();
    }
    @Override
    public void exportData(String userName, String pdfFilePath) throws SystemBusyException, BadRequestException, NotFoundException {
        try {
            String userId = userName;
            List<UserActivity> userActivities = getRetry(userName);

            List<String> data = new ArrayList<>();
            data.add("User ID: " + userId);
            data.add("User Activities:");

            for (UserActivity activity : userActivities) {
                data.add(formatUserActivity(activity));
            }

            try {
                PdfExporter.exportDataToPdf(data, pdfFilePath);
                System.out.println("UserActivity data exported to: " + pdfFilePath);
            } catch (IOException e) {
                logger.error("Error exporting UserActivity data to PDF", e);
            }
        } catch (SystemBusyException e) {
        System.out.println("System busy, unable to retrieve user activities. Please try again later.");
    }
    }


    public List getRetry(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
        int maxRetries = 3;
        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                UserActivityService userActivityService = new UserActivityService();
                return userActivityService.getUserActivity(userName);
            } catch (SystemBusyException e) {
                retryCount++;
                if (retryCount < maxRetries) {
                    System.out.println("System busy, retrying in 1 second...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    throw e;
                }
            }
        }
        return Collections.emptyList();
    }

}





