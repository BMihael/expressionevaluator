package leapwise.soft.expressionevaluator.controller;

import leapwise.soft.expressionevaluator.model.form.ExpressionProcessForm;

public interface ExpressionController {
    String processExpression(ExpressionProcessForm expressionProcessForm);

    String evaluateExpression(String id, String jsonRaw);
}
