package edu.najah.cap.ExceptionHandling;

public class ValidateUser extends RuntimeException{
    public ValidateUser(String message) {
        super(message);
    }
}
