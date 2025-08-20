package com.anmol.week1.IntroductionToSpringBoot.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


//defined as data so that we can use getters and setters
@Data
@Builder
public class ApiError {

    private HttpStatus status;

    private String message;

}
