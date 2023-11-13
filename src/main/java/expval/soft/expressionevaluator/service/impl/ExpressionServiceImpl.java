package expval.soft.expressionevaluator.service.impl;

import expval.soft.expressionevaluator.exception.ExpressionWithGivenNameAlreadyExistsException;
import expval.soft.expressionevaluator.exception.ExpressionWithIdDoesNotExistException;
import expval.soft.expressionevaluator.model.Expression;
import expval.soft.expressionevaluator.model.form.ExpressionEvaluationForm;
import expval.soft.expressionevaluator.model.form.ExpressionProcessForm;
import expval.soft.expressionevaluator.repository.ExpressionRepository;
import expval.soft.expressionevaluator.service.ExpressionService;
import expval.soft.expressionevaluator.structures.tree.TreeProvider;
import expval.soft.expressionevaluator.util.ExpressionStripper;
import expval.soft.expressionevaluator.util.ExpressionStripperValues;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExpressionServiceImpl implements ExpressionService {

  private static Logger logger = LoggerFactory.getLogger(ExpressionServiceImpl.class);

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
    logger.info("Generated identifier: " + e.getIdentifier());
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
