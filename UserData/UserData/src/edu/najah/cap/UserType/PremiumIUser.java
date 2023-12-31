package edu.najah.cap.UserType;

import edu.najah.cap.Export.ExportService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

public class PremiumIUser implements IUserType {
    @Override
    public void exportUserData(ExportService exportService, String loginUserName) throws SystemBusyException, BadRequestException, NotFoundException {
        exportService.exportData("UserActivity", loginUserName, "UserActivityForPREMIUM_USER");
        exportService.exportData("Payment", loginUserName, "PaymentForPREMIUM_USER");
       exportService.exportData("UserProfile", loginUserName, "UserProfileForREMIUM_USER");
       exportService.exportData("Post", loginUserName, "PostForPREMIUM_USER");
    }
}
