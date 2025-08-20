package com.anmol.week1.IntroductionToSpringBoot.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


// When an exception is thrown in any of @RestController, Spring will look for a matching @ExceptionHandler method in classes annotated
// with @RestControllerAdvice.
//If any exception is being occurred in any of the controllers then all those exceptions are being handled inside this class hence we have
// denoted/annotated it with @RestControllerAdvice

@RestControllerAdvice
public class GlobalExceptionHandler {

//this below code will handle all the NoSuchElementException occurred in any of the controllers

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> NoSuchUserFound(NoSuchElementException e){

    // this below code will work correctly in postman if we try to find a user whose ID is present inside DB but in status code it will
    // show 200oK so to handle it will use , new ResponseEntity<>("Employee with such ID is not Present",HttpStatus.NOT_FOUND);
    //  below code is this -> return "Employee of given ID is not Present";

//  The below code is running perfectly But we have commented it because we have return ApiError in response because ApiError has more detail
   return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);


//this ApiError was not running it was showing cannot resolve builder even though we have imported builder
//inside the ApiError .
//        ApiError apiError = ApiError.
//                builder()
//                .status(HttpStatus.NOT_FOUND)
//                .message("Resource/employee with given ID not found")
//                .build();
//        return new ResponseEntity<>(apiError , HttpStatus.NOT_FOUND) ;
//
//        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND , "element not found" );
//
//        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);

    }
}
