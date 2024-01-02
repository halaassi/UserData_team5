package edu.najah.cap.Export;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

public interface Exporter {
    void exportData(String userName, String filePath) throws SystemBusyException, BadRequestException, NotFoundException;
}
