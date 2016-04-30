package com.lapsa.country.validators;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lapsa.country.Country;
import com.lapsa.validators.AllowDenyOrder;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidCountryConstraintValidator.class)
public @interface ValidCountry {

    Country[] alloweValues() default {};

    Country[] denyValues() default {};

    String message() default "{com.lapsa.country.validators.ValidCountry.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    AllowDenyOrder mode() default AllowDenyOrder.ALLOW_ALL_NOT_DENIED;

}