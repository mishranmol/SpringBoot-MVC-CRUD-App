package com.anmol.week1.IntroductionToSpringBoot.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> NoSuchUserFound(NoSuchElementException e){

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
