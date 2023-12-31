package expval.soft.expressionevaluator.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExpressionNameFieldValidator.class)
public @interface ValidExpressionForm {
  String name() default "";

  String value() default "";

  String message() default "Invalid ExpressionForm";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
