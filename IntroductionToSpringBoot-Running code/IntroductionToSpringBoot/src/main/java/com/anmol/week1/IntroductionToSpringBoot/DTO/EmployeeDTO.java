package com.anmol.week1.IntroductionToSpringBoot.DTO;

import com.anmol.week1.IntroductionToSpringBoot.annotations.EmployeeRoleValidationAnnotation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDTO {


    private Long Id;

    //this is how we can put multiple validation checks on Field="name" 1st check->It should be not empty and 2nd check-> is size/length
    @NotBlank(message = "Name should not be Blank")
    //it will check the length of string should between 3 and 10 both inclusive
    @Size(min = 3 , max = 10 , message = "Characters should be in Range [3,10]")
    private String name;

    @NotBlank(message = "Email Should not be Blank")
    @Email(message = "Email should be in valid format")
    private String email;


    @NotNull(message = "Age should not be null")
    @Min(value = 18 , message = "Min Age should be 18")
    @Max(value = 80 , message = "Max Age should be exceed 80")
    private int age;

    @NotBlank(message = "Role should not be blank")
    //creating our own custom annotation down
    @EmployeeRoleValidationAnnotation
//  @Pattern(regexp = "^(ADMIN|USER)$" , message = "Role can either be USER or ADMIN")
    //it means that role can either be "ADMIN" or "USER"
    private String role; // ADMIN OR USER

    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date should either be in Past or Present")
    private LocalDate dataOfJoining;

    @NotNull(message = "Salary Should not be Blank")
    @Positive(message = "Salary cannot be negative")
    @Digits(integer = 5 , fraction = 2 , message = "Salary format should be XXXXX.YY")
    //integer = 5 means no. of digits before the decimal can be UPTO 5 and fraction = 2 means after the decimal it can only be UPTO 2
    @DecimalMin(value = "100.99" , message = "minimum salary should be 100.99")
    //means minimum value of salary can be 100.99
    @DecimalMax(value = "99999.99" , message = "maximum salary can be 99999.99")
    //means maximum value of salary can be 99999.99
    private Double salary;


//    @AssertTrue(message = "Employee should be True always")
    private boolean isActive;

}
