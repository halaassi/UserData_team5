package edu.najah.cap.menu;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;

public interface Menu {
     void menu(int index) throws SystemBusyException, NotFoundException, BadRequestException;
}
