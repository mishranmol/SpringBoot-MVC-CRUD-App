package com.anmol.week1.IntroductionToSpringBoot.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;

//Note-> This class implements ConstraintValidator and it contains two things <Name of Annotation , which type of field we want to validate>
//Make sure that this method implements isValid() method and this method always return boolean value
public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidationAnnotation, String> {

    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext){
        if(inputRole==null) return false;
        ArrayList<String> roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
        return roles.contains(inputRole);
    }

}
