package com.anmol.week1.IntroductionToSpringBoot.annotations;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



//we have to apply our custom annotation only above fields in our case
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
//connecting the annotation with the constraint
@Constraint(validatedBy = {EmployeeRoleValidator.class})
public @interface EmployeeRoleValidationAnnotation {
    String message() default "Role can either be USER or ADMIN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
