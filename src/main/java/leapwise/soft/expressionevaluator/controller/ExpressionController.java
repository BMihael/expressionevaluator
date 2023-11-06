package leapwise.soft.expressionevaluator.controller;

import leapwise.soft.expressionevaluator.model.form.ExpressionProcessForm;

public interface ExpressionController {

  String PATH_PROCESS_EXPRESSION = "/expression";
  String PATH_EVALUATE_EXPRESSION = "/evaluate";

  String processExpression(ExpressionProcessForm expressionProcessForm);

  String evaluateExpression(String id, String jsonRaw);
}
