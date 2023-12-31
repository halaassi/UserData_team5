package edu.najah.cap.menu;
import edu.najah.cap.deletion.*;
import edu.najah.cap.exceptions.*;
import edu.najah.cap.iam.*;
import org.apache.log4j.Logger;
import java.util.Scanner;
import static edu.najah.cap.data.Application.getLoginUserName;
import static edu.najah.cap.deletion.GetUserType.getUserType;

public class SwitchMenu implements Menu {
    private static final Logger logger = Logger.getLogger(SwitchMenu.class);
    @Override
    public void menu(int index) throws SystemBusyException, NotFoundException, BadRequestException {
        Scanner scanner = new Scanner(System.in);
        IUserService userService = new UserService();
        displayUser displayUser = new Display();
        UserType userType = getUserType(getLoginUserName());


        switch (index) {
            case 1:
                //1-Exporting Data
                break;
            case 2:
              //  2-Upload Data
                break;
            case 3:
                logger.info("if you need to Soft delete Click 1 and if you need to hard delete Click 2 ");
                double num1 = Double.parseDouble(scanner.nextLine());
                DeletionStrategy deletionStrategy;

                if (num1 == 1)
                {
                    UserProfile userToDelete = userService.getUser(getLoginUserName());
                    deletionStrategy = new SoftDelete();
                    deletionStrategy.delete(userToDelete, String.valueOf(userType));

                }
                else if (num1 == 2)
                {
                    UserProfile userToDelete = userService.getUser(getLoginUserName());
                    deletionStrategy = new HardDeleteStrategy();
                    deletionStrategy.delete(userToDelete, String.valueOf(userType));
                }

                break;

            case 4:
                displayUser.displayUserData(getLoginUserName());

                break;
            case 5:
                logger.info("add user name ");
                String userName = scanner.nextLine();
                UsarAddFromUser adduser;
                adduser = new adduserfromuser();
                adduser.add(userName);

                break;
            case 6:
                logger.info("exit the program. Goodbye!");
                System.exit(0);
            default:
                logger.info("Invalid option. Please choose again.");
        }

        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
