package edu.najah.cap.Upload;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

public interface Upload {
   public void uploadFile(String userId) throws SystemBusyException, BadRequestException, NotFoundException;
}
