package expval.soft.expressionevaluator.validation;

import expval.soft.expressionevaluator.model.form.ExpressionProcessForm;
import expval.soft.expressionevaluator.exception.NameNotValidException;
import expval.soft.expressionevaluator.exception.ValueNotValidException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static expval.soft.expressionevaluator.util.ExpressionStripperValues.NAME_FIELD_PREFIX;
import static expval.soft.expressionevaluator.util.ExpressionStripperValues.VALUE_FIELD_PREFIX;

public class ExpressionNameFieldValidator
    implements ConstraintValidator<ValidExpressionForm, ExpressionProcessForm> {

  public static final String FIELD_NAME_IS_MANDATORY = "Field name is mandatory";
  public static final String FIELD_VALUE_IS_MANDATORY = "Field value  is mandatory";
  public static final String FIELD_VALUE_DOES_NOT_MEET_CRITERIA =
      "Field value does not meet criteria";
  public static final String FIELD_NAME_DOES_NOT_MEET_CRITERIA =
      "Field name does not meet criteria";

  private String name;
  private String value;

  @Override
  public void initialize(ValidExpressionForm form) {
    name = form.name();
    value = form.value();
  }

  @Override
  public boolean isValid(
      ExpressionProcessForm expressionProcessForm, ConstraintValidatorContext context) {
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
      throw new NameNotValidException(FIELD_NAME_IS_MANDATORY);
    }
    if (!(name.startsWith(NAME_FIELD_PREFIX.toString()))) {
      throw new NameNotValidException(FIELD_NAME_DOES_NOT_MEET_CRITERIA);
    }
  }

  private void isValueValid(String value) {
    if (value == null) {
      throw new ValueNotValidException(FIELD_VALUE_IS_MANDATORY);
    }
    if (!(value.startsWith(VALUE_FIELD_PREFIX.toString()))) {
      throw new ValueNotValidException(FIELD_VALUE_DOES_NOT_MEET_CRITERIA);
    }
  }
}
