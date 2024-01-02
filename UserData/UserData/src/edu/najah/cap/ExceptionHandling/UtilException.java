package edu.najah.cap.ExceptionHandling;
public class UtilException {
    private UtilException(){}
    public static void validateName(String userName) throws ValidateUser
    {
        int userIndex = Integer.parseInt(userName.replaceAll("[^0-9]", ""));
        if (userIndex > 99) {
            throw new ValidateUser("User is greater than 99 .");
        }

    }}
