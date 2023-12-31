package edu.najah.cap.Upload;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConvertToPDF {

    public InputStream convertTextFileToPdf(String txtFilePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                String txtContent = readTextFile(txtFilePath);

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
                contentStream.beginText();
                contentStream.newLineAtOffset(10, 700);
                for (String line : txtContent.split("\n")) {
                    contentStream.showText(line);
                    contentStream.newLine();
                    contentStream.newLineAtOffset(0, -20);
                }
                contentStream.endText();
            }

            // Save the document to a ByteArrayOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }
    }

    private String readTextFile(String txtFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(txtFilePath, StandardCharsets.UTF_8))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        }
    }
}