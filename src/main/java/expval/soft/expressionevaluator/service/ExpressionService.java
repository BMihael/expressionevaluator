package expval.soft.expressionevaluator.service;

import expval.soft.expressionevaluator.model.form.ExpressionEvaluationForm;
import expval.soft.expressionevaluator.model.form.ExpressionProcessForm;

public interface ExpressionService {
  String processExpression(ExpressionProcessForm expressionProcessForm);

  String evaluateExpression(ExpressionEvaluationForm expressionEvaluationForm);
}
