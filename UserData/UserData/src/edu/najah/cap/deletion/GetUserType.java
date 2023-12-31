package edu.najah.cap.deletion;

import edu.najah.cap.iam.UserType;

import static edu.najah.cap.data.Application.getRandomUserType;

public class GetUserType {
    private GetUserType(){}
    public static UserType getUserType(String userName) {
        int userIndex = Integer.parseInt(userName.replaceAll("[^0-9]", ""));
        UserType userType = getRandomUserType(userIndex);
        return userType;
    }
}
