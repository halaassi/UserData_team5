package edu.najah.cap.UserType;

import edu.najah.cap.Export.ExportService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

public interface IUserType {
    void exportUserData(ExportService exportService, String loginUserName) throws SystemBusyException, BadRequestException, NotFoundException;

}
