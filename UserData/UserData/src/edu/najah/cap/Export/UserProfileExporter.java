package edu.najah.cap.Export;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class UserProfileExporter implements Exporter {

    private String formatUserProfile(UserProfile userProfile) {
        return "User Name: " + userProfile.getUserName() + "First Name: " + userProfile.getFirstName() +"Last Name: " + userProfile.getLastName() +"phone: " + userProfile.getPhoneNumber() +", Email: " + userProfile.getEmail() +", Password: " + userProfile.getPassword() +", Role: " + userProfile.getRole() +", Department: " + userProfile.getDepartment() +", Organization: " + userProfile.getOrganization() +", Country: " + userProfile.getCountry() +", city: " + userProfile.getCity() +", street: " + userProfile.getStreet() +", postal: " + userProfile.getPostalCode()+", building: " + userProfile.getBuilding();
    }
    public void exportData(String userName, String pdfFilePath) throws BadRequestException, NotFoundException {
        try {
            UserProfile userProfile = getRetry(userName);

            List<String> data = new ArrayList<>();
            data.add("User Name: " + userName);
            data.add("User Profile: " + formatUserProfile(userProfile));

            try {
                PdfExporter.exportDataToPdf(data, pdfFilePath);
                System.out.println("User profile data exported to: " + pdfFilePath);
            } catch (IOException e) {
                throw new RuntimeException("Error exporting UserProfile data to PDF", e);
            }
        } catch (SystemBusyException e) {
            System.out.println("System busy, unable to retrieve user. Please try again later.");
        }
    }

    UserProfile getRetry(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
        int maxRetries = 3;
        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                UserService userService = new UserService();
                return userService.getUser(userName);
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
        throw new NotFoundException("User profile not found for user: " + userName);
    }

}
