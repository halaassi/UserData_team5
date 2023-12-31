package edu.najah.cap.menu;
import edu.najah.cap.adduser.UsarAddFromUser;
import edu.najah.cap.adduser.adduserfromuser;
import edu.najah.cap.deletion.*;

import edu.najah.cap.display.Display;
import edu.najah.cap.display.displayUser;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.*;
import org.apache.log4j.Logger;
import java.util.Scanner;
import static edu.najah.cap.data.Application.getLoginUserName;

public class SwitchMenu implements Menu {
    private static final Logger logger = Logger.getLogger(SwitchMenu.class);

    @Override
    public void menu(int index)  {
        Scanner scanner = new Scanner(System.in);
        IUserService userService = new UserService();
        UserProfile userProfile = new UserProfile();
        displayUser displayUser = new Display();
        UserType userType = userProfile.getUserType();

        try {
            userProfile=userService.getUser(getLoginUserName());


        } catch (SystemBusyException | BadRequestException | NotFoundException e) {
            throw new RuntimeException(e);
        }

        switch (index) {
            case 1:
                //1-Exporting Data
                break;
            case 2:
              //  2-Upload Data
                break;
            case 3:
                System.out.println("if you need to Soft delete Click 1 and if you need to hard delete Click 2 ");
                double num1 = Double.parseDouble(scanner.nextLine());
                DeletionStrategy deletionStrategy;

                if (num1 == 1)
                {
                    try {
                        UserProfile userToDelete = userService.getUser(getLoginUserName());
                        deletionStrategy = new SoftDelete();
                        deletionStrategy.delete(userToDelete, String.valueOf(userType));
                    } catch (NotFoundException | SystemBusyException | BadRequestException e) {
                        logger.error("Error while soft deleting user", e);
                    }


                }
                else if (num1 == 2)
                {
                    try {
                        UserProfile userToDelete = userService.getUser(getLoginUserName());
                        deletionStrategy = new HardDeleteStrategy();
                        deletionStrategy.delete(userToDelete, String.valueOf(userType));
                    }
                    catch (NotFoundException | SystemBusyException | BadRequestException e) {
                        logger.error("Error while hard deleting user", e);
                    }
                }

                break;

            case 4:
                displayUser.displayUserData(getLoginUserName());

                break;
            case 5:
                System.out.println("add user name ");
                String userName = scanner.nextLine();
                UsarAddFromUser adduser;
                adduser = new adduserfromuser();
                adduser.add(userName);

                break;
            case 6:
                System.out.println("exit the program. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please choose again.");

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
