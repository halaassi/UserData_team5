package edu.najah.cap.Upload;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ConvertToZip {

    public static void createZipFile(String zipFilePath, List<ByteArrayOutputStream> pdfByteArrays) {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (int i = 0; i < pdfByteArrays.size(); i++) {
                addByteArrayToZip(pdfByteArrays.get(i), zos, "Document" + (i + 1) + ".pdf");
            }

            System.out.println("Zip file created: " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addByteArrayToZip(ByteArrayOutputStream byteArray, ZipOutputStream zos, String entryName) throws IOException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(byteArray.toByteArray())) {
            ZipEntry zipEntry = new ZipEntry(entryName);
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = bis.read(buffer)) > 0) {
                zos.write(buffer, 0, bytesRead);
            }

            zos.closeEntry();
        }
    }
}
