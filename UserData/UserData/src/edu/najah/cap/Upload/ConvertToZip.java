package edu.najah.cap.Upload;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ConvertToZip {

    public static void createZipFile(String sourceFilePath, String zipFilePath) {
        try {
            File sourceFile = new File(sourceFilePath);

            if (!sourceFile.exists()) {
                System.err.println("Source file does not exist");
                return;
            }

            FileOutputStream fos = new FileOutputStream(zipFilePath);
            ZipOutputStream zos = new ZipOutputStream(fos);

            // Add the source file to the zip archive
            addFileToZip(sourceFile, zos);

            zos.close();
            fos.close();
            System.out.println("Zip file created: " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addFileToZip(File file, ZipOutputStream zos) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(file.getName());
        zos.putNextEntry(zipEntry);

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, bytesRead);
        }

        fis.close();
        zos.closeEntry();
    }
}

