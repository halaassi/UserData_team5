package edu.najah.cap.ExceptionHandling;

import edu.najah.cap.exceptions.BadRequestException;


public class UtilException {
    private UtilException(){}

    public static void validateName(String userName)throws BadRequestException
    {
        int userIndex = Integer.parseInt(userName.replaceAll("[^0-9]", ""));
        if (userIndex > 99) {
            throw new BadRequestException("User is greater than 99 .");
        }

    }}
//    public static void ValidUserType(String userType)throws SystemBusyException,BadRequestException
//    {
//        if (!("NEW_USER".equals(userType) | "REGULAR_USER".equals(userType)| "PREMIUM_USER".equals(userType)))
//            throw new BadRequestException("Invalid userType ");
//        }
//
//    }
