package com.anmol.week1.IntroductionToSpringBoot.advices;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

//all fields are private so have to use getters here hence @Data
@Data
public class ApiResponse<T> {

    private LocalDateTime timestamp;

    private T data;

    private  ApiError error;


    //default constructor
    //this will create timestamp for every response
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();//calling default constructor
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();//calling default constructor
        this.error = error;
    }
}
