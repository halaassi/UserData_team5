package edu.najah.cap.Export;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

import java.io.File;

public class ExportService {
    private ExporterFactory exporterFactory;

    public ExportService(ExporterFactory exporterFactory) {
        this.exporterFactory = exporterFactory;
    }

    public void exportData(String dataType, String userName, String baseFilePath) throws SystemBusyException, BadRequestException, NotFoundException {
        String folderPath = userName+ "/" ;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String filePath =folderPath+ baseFilePath + "_" + userName + ".pdf";

        Exporter exporter = exporterFactory.createExporter(dataType, userName);
        exporter.exportData(userName, filePath);

    }
}