package edu.najah.cap.Upload;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConvertToPDF {

    public static ByteArrayOutputStream convertUserProfileToPDF(UserProfile userProfile) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            try (contentStream) {
                contentStream.beginText();
                contentStream.newLineAtOffset(20, 700);
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12);

                String userProfileString = String.format(
                        "User Name: %s" +
                                "Full Name: %s %s" +
                                "Email: %s" +
                                "Phone Number: %s" +
                                "Role: %s" +
                                "Department: %s" +
                                "Organization: %s" +
                                "Country: %s" +
                                "City: %s" +
                                "Street: %s" +
                                "Postal Code: %s" +
                                "Building: %s" +
                                "User Type: %s",
                        userProfile.getUserName(),
                        userProfile.getFirstName(),
                        userProfile.getLastName(),
                        userProfile.getEmail(),
                        userProfile.getPhoneNumber(),
                        userProfile.getRole(),
                        userProfile.getDepartment(),
                        userProfile.getOrganization(),
                        userProfile.getCountry(),
                        userProfile.getCity(),
                        userProfile.getStreet(),
                        userProfile.getPostalCode(),
                        userProfile.getBuilding(),
                        userProfile.getUserType()
                );

                contentStream.showText(userProfileString);


            }catch (Exception e) {
                e.printStackTrace();
                System.err.println("Exception message: " + e.getMessage());
                throw e;
            } finally {
                contentStream.endText();
            }


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);

            return outputStream;
        }
    }



    public static ByteArrayOutputStream convertTransactionsToPDF(List<Transaction> transactions) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            try (contentStream) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);

                for (Transaction transaction : transactions) {
                    String transactionString = String.format(
                            "Amount: %s | Timestamp: %s",
                            transaction.getAmount(),
                            transaction.getDescription()
                    );

                    contentStream.showText(transactionString);
                    contentStream.newLineAtOffset(0, -20);
                }

            }catch (Exception e) {
                e.printStackTrace();
                System.err.println("Exception message: " + e.getMessage());
                throw e;
            } finally {
                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);

            return outputStream;
        }
    }
    public static ByteArrayOutputStream convertPostsToPDF(List<Post> posts) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            try (contentStream) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12);
                contentStream.newLineAtOffset(20, 700);

                for (Post post : posts) {
                    String postString = String.format(
                            "Title: %s | Body: %s | Author: %s | Date: %s",
                            post.getTitle(),
                            post.getBody(),
                            post.getAuthor(),
                            post.getDate()
                    );
                    contentStream.showText(postString);
                    contentStream.newLineAtOffset(0, -20);
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Exception message: " + e.getMessage());
                throw e;
            } finally {
                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);

            return outputStream;
        }
    }

    public static ByteArrayOutputStream convertUserActivitiesToPDF(List<UserActivity> userActivities) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            try (contentStream) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12);
                contentStream.newLineAtOffset(20, 700);

                for (UserActivity activity : userActivities) {
                    String activityString = String.format(
                                    "Activity Type: %s | Activity Date: %s",
                            activity.getActivityType(),
                            activity.getActivityDate()
                    );
                    contentStream.showText(activityString);
                    contentStream.newLineAtOffset(0, -20);
                }


            }catch (Exception e) {
                e.printStackTrace();
                System.err.println("Exception message: " + e.getMessage());
                throw e;
            } finally {
                contentStream.endText();
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);

            return outputStream;
        }
    }


}