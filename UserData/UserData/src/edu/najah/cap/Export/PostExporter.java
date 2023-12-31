package edu.najah.cap.Export;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.posts.Post;
import edu.najah.cap.posts.PostService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostExporter implements Exporter {
    private String formatPost(Post post) {
        return "Title: " + post.getTitle() +"Body: " + post.getBody() + ", user: " + post.getAuthor();
    }
    @Override
    public void exportData(String userName, String pdfFilePath) throws SystemBusyException, BadRequestException, NotFoundException {
        String author = userName;
        List<Post> posts = getRetry(userName);

        List<String> data = new ArrayList<>();
        data.add("Author: " + author);
        data.add("Posts:");

        for (Post post : posts) {
            data.add(formatPost(post));
        }

        try {
            PdfExporter.exportDataToPdf(data, pdfFilePath);
            System.out.println("Post data exported to: " + pdfFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Error exporting Post data to PDF", e);
        }
    }


    public List getRetry(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
        int maxRetries = 3;
        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                PostService post = new PostService();
                return post.getPosts(userName);
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


