package edu.najah.cap.Export;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.payment.PaymentService;
import edu.najah.cap.payment.Transaction;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentExporter implements Exporter {
    private final Logger logger = Logger.getLogger(getClass());

    private String formatPayment(Transaction transaction) {
        return "User Name: " + transaction.getUserName() +
                ", Amount: " + transaction.getAmount() +
                ", Description: " + transaction.getDescription();
    }

    @Override
    public void exportData(String userName, String pdfFilePath) throws BadRequestException, NotFoundException {
        try {
            List<Transaction> transactions = getRetry(userName);

            List<String> data = new ArrayList<>();
            data.add("User Name: " + userName);
            data.add("Transactions:");

            for (Transaction transaction : transactions) {
                data.add(formatPayment(transaction));
            }

            try {
                PdfExporter.exportDataToPdf(data, pdfFilePath);
                System.out.println("Payment data exported to: " + pdfFilePath);
            } catch (IOException e) {
                logger.error("Error exporting Payment data to PDF", e);
            }
        } catch (SystemBusyException e) {
            System.err.println("System busy, unable to retrieve transactions. Please try again later.");
        }
    }

     public List<Transaction> getRetry(String userName) throws SystemBusyException, BadRequestException, NotFoundException {
        int maxRetries = 3;
        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                PaymentService paymentService = new PaymentService();
                return paymentService.getTransactions(userName);
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

