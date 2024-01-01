package edu.najah.cap.ExceptionHandling;

import java.time.Instant;


public class UtilException {
    private UtilException(){}

    public static void validateName(String userName) throws validateuser
    {
        int userIndex = Integer.parseInt(userName.replaceAll("[^0-9]", ""));
        if (userIndex > 99) {
            throw new validateuser("User is greater than 99 .");
        }

    }}
