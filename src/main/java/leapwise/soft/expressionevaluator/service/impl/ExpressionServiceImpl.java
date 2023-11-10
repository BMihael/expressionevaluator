package leapwise.soft.expressionevaluator.service.impl;

import leapwise.soft.expressionevaluator.algorithm.tree.TreeProvider;
import leapwise.soft.expressionevaluator.exception.ExpressionWithGivenNameAlreadyExistsException;
import leapwise.soft.expressionevaluator.exception.ExpressionWithIdDoesNotExistException;
import leapwise.soft.expressionevaluator.model.Expression;
import leapwise.soft.expressionevaluator.model.form.ExpressionEvaluationForm;
import leapwise.soft.expressionevaluator.model.form.ExpressionProcessForm;
import leapwise.soft.expressionevaluator.repository.ExpressionRepository;
import leapwise.soft.expressionevaluator.service.ExpressionService;
import leapwise.soft.expressionevaluator.util.ExpressionStripper;
import leapwise.soft.expressionevaluator.util.ExpressionStripperValues;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExpressionServiceImpl implements ExpressionService {

  public static final String EXCEPTION_WITH_NAME_ALREADY_EXISTS_IN_DATABASE =
      "An exception with the provided name already exists in the database.";
  public static final String EXCEPTION_ASSOCIATED_WITH_THE_PROVIDED_IDENTIFIER_DOES_NOT_EXIST =
      "The exception associated with the provided identifier does not exist";

  private final ExpressionRepository expressionRepository;

  public ExpressionServiceImpl(ExpressionRepository expressionRepository) {
    this.expressionRepository = expressionRepository;
  }

  @Override
  public String processExpression(ExpressionProcessForm expressionProcessForm) {
    String name =
        ExpressionStripper.stripField(
            expressionProcessForm.getName(), ExpressionStripperValues.NAME_FIELD_PREFIX);
    checkIfExpressionNameExists(name);

    String value =
        ExpressionStripper.stripField(
            expressionProcessForm.getValue(), ExpressionStripperValues.VALUE_FIELD_PREFIX);

    Expression e =
        expressionRepository.save(
            Expression.builder()
                .name(name)
                .expressionValue(value)
                .identifier(UUID.randomUUID().toString())
                .build());
    return e.getIdentifier();
  }

  @Override
  public String evaluateExpression(ExpressionEvaluationForm expressionEvaluationForm) {
    JSONObject input = new JSONObject(expressionEvaluationForm.getJsonRaw());
    String id = expressionEvaluationForm.getId();
    Expression expression = findExpressionByIdentifier(id);
    TreeProvider.provideExpression(expression.getExpressionValue());
    TreeProvider.fillTreeHelper(input);
    return TreeProvider.printResult();
  }

  private void checkIfExpressionNameExists(String name) {
    Expression expression = expressionRepository.findExpressionByName(name);
    if (expression != null) {
      throw new ExpressionWithGivenNameAlreadyExistsException(
          EXCEPTION_WITH_NAME_ALREADY_EXISTS_IN_DATABASE);
    }
  }

  private Expression findExpressionByIdentifier(String id) {
    Expression expression = expressionRepository.findExpressionByIdentifier(id);
    if (expression == null) {
      throw new ExpressionWithIdDoesNotExistException(
          EXCEPTION_ASSOCIATED_WITH_THE_PROVIDED_IDENTIFIER_DOES_NOT_EXIST);
    }
    return expression;
  }
}
