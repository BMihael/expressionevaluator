package expval.soft.expressionevaluator.controller;

import expval.soft.expressionevaluator.model.form.ExpressionProcessForm;

public interface ExpressionController {

  String PATH_PROCESS_EXPRESSION = "/expression";
  String PATH_EVALUATE_EXPRESSION = "/evaluate";

  String processExpression(ExpressionProcessForm expressionProcessForm);

  String evaluateExpression(String id, String jsonRaw);
}
