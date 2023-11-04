package leapwise.soft.expressionevaluator.validation;

import leapwise.soft.expressionevaluator.exception.NameNotValidException;
import leapwise.soft.expressionevaluator.exception.ValueNotValidException;
import leapwise.soft.expressionevaluator.model.form.ExpressionProcessForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExpressionNameFieldValidator implements ConstraintValidator<ValidExpressionForm, ExpressionProcessForm> {
    public static final String NAME_FIELD_PREFIX = "Name: ";
    public static final String VALUE_FIELD_PREFIX = "Value: ";

    private String name;
    private String value;

    @Override
    public void initialize(ValidExpressionForm form) {
        name = form.name();
        value = form.value();
    }

    @Override
    public boolean isValid(ExpressionProcessForm expressionProcessForm, ConstraintValidatorContext context) {
        if (expressionProcessForm == null) {
            return true;
        }

        if (name.equals("name")) {
             isNameValid(expressionProcessForm.getName());
        }

        if (value.equals("value")) {
            isValueValid(expressionProcessForm.getValue());
        }
        return true;
    }

    private void isNameValid(String name) {
        if (name == null) {
            throw new NameNotValidException("Field name cannot be null");
        }
        if(!(name.startsWith(NAME_FIELD_PREFIX))){
            throw new NameNotValidException("Field name does not meet criteria");
        }
    }

    private void isValueValid(String value) {
        if (value == null) {
            throw new ValueNotValidException("Field value cannot be null");
        }
        if(!(value.startsWith(VALUE_FIELD_PREFIX))){
            throw new ValueNotValidException("Field value does not meet criteria");
        }
    }
}