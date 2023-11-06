package leapwise.soft.expressionevaluator.service;

import leapwise.soft.expressionevaluator.model.form.ExpressionEvaluationForm;
import leapwise.soft.expressionevaluator.model.form.ExpressionProcessForm;

public interface ExpressionService {
  String processExpression(ExpressionProcessForm expressionProcessForm);

  String evaluateExpression(ExpressionEvaluationForm expressionEvaluationForm);
}
