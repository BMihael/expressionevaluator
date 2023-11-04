package leapwise.soft.expressionevaluator.validation;

import leapwise.soft.expressionevaluator.exception.NameNotValidException;
import leapwise.soft.expressionevaluator.exception.ValueNotValidException;
import leapwise.soft.expressionevaluator.model.form.ExpressionProcessForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExpressionNameFieldValidator implements ConstraintValidator<ValidExpressionForm, ExpressionProcessForm> {
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
            throw new NameNotValidException("Name cannot be null");
        }
        boolean doesNameBeginWithCertainWord = (name.substring(0,6).equals("Name: "));
        if(!doesNameBeginWithCertainWord){
            throw new NameNotValidException("Name does not meet criteria");
        }
    }

    private void isValueValid(String value) {
        if (value == null) {
            throw new ValueNotValidException("Value cannot be null");
        }
        boolean doesValueBeginWithCertainWord = value.substring(0,7).equals("Value: ");
        if(!doesValueBeginWithCertainWord){
            throw new ValueNotValidException("Value does not meet criteria");
        }
    }
}