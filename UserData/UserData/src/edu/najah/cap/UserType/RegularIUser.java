package edu.najah.cap.UserType;

import edu.najah.cap.Export.ExportService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

public class RegularIUser implements IUserType {
    @Override
    public void exportUserData(ExportService exportService, String loginUserName) throws SystemBusyException, BadRequestException, NotFoundException {
        exportService.exportData("UserActivity", loginUserName, "UserActivityForREGULAR_USER ");
        exportService.exportData("UserProfile", loginUserName, "UserProfileForREGULAR_USER");
        exportService.exportData("Post", loginUserName, "PostForREGULAR_USER");
    }
}
