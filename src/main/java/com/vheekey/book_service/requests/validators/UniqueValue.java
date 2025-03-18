package com.vheekey.book_service.requests.validators;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValue.UniqueValueValidator.class)
@Documented
public @interface UniqueValue {
    String message() default "This value already exists";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    String field();

    Class<?> collection();

    @Component
    class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

        private final MongoTemplate mongoTemplate;
        private String field;
        private Class<?> collection;

        public UniqueValueValidator(MongoTemplate mongoTemplate) {
            this.mongoTemplate = mongoTemplate;
        }

        @Override
        public void initialize(UniqueValue constraintAnnotation) {
            this.field = constraintAnnotation.field();
            this.collection = constraintAnnotation.collection();
        }

        @Override
        public boolean isValid(String fieldValue, ConstraintValidatorContext context) {
            if (fieldValue == null) {
                return true;
            }

            String fieldName = field.isEmpty() ? context.getDefaultConstraintMessageTemplate() : field;
            Query query = Query.query(Criteria.where(fieldName).is(fieldValue));
            return !mongoTemplate.exists(query, collection);
        }
    }
}
