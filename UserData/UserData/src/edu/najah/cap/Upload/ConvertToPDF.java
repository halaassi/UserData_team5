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
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);

                String userProfileString = String.format(
                        "User Name: %s\n" +
                                "Full Name: %s %s\n" +
                                "Email: %s\n" +
                                "Phone Number: %s\n" +
                                "Role: %s\n" +
                                "Department: %s\n" +
                                "Organization: %s\n" +
                                "Country: %s\n" +
                                "City: %s\n" +
                                "Street: %s\n" +
                                "Postal Code: %s\n" +
                                "Building: %s\n" +
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


            }
            contentStream.endText();
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
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);

                for (Transaction transaction : transactions) {
                    String transactionString = String.format(
                            "ID: %s | User: %s | Amount: %s | Timestamp: %s",
                            transaction.getId(),
                            transaction.getUserName(),
                            transaction.getAmount(),
                            transaction.getDescription()
                    );

                    contentStream.showText(transactionString);
                    contentStream.newLineAtOffset(0, -20);
                }


            }
            contentStream.endText();
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
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(20, 700);

                for (Post post : posts) {
                    String postString = String.format(
                            "Title: %s\n" +
                                    "Body: %s\n" +
                                    "Author: %s\n" +
                                    "Date: %s\n\n",
                            post.getTitle(),
                            post.getBody(),
                            post.getAuthor(),
                            post.getDate()
                    );
                    contentStream.showText(postString);
                }


            }
            contentStream.endText();
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
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(20, 700);

                for (UserActivity activity : userActivities) {
                    String activityString = String.format(
                            "User ID: %s\n" +
                                    "Activity Type: %s\n" +
                                    "Activity Date: %s\n\n",
                            activity.getUserId(),
                            activity.getActivityType(),
                            activity.getActivityDate()
                    );
                    contentStream.showText(activityString);
                }


            }
            contentStream.endText();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream;
        }
    }


}