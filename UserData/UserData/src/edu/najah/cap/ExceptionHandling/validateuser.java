package edu.najah.cap.ExceptionHandling;

import edu.najah.cap.exceptions.BadRequestException;


public class validateuser extends RuntimeException{
    public  validateuser(String message)throws  BadRequestException  {
        super(message);
    }
}
