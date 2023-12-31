package expval.soft.expressionevaluator.controller.impl;

import expval.soft.expressionevaluator.controller.ExpressionController;
import expval.soft.expressionevaluator.model.form.ExpressionEvaluationForm;
import expval.soft.expressionevaluator.model.form.ExpressionProcessForm;
import expval.soft.expressionevaluator.service.impl.ExpressionServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
public class ExpressionControllerImpl implements ExpressionController {

  private final ExpressionServiceImpl expressionServiceImpl;

  public ExpressionControllerImpl(ExpressionServiceImpl expressionServiceImpl) {
    this.expressionServiceImpl = expressionServiceImpl;
  }

  @Override
  @PostMapping(PATH_PROCESS_EXPRESSION)
  public String processExpression(@Valid @RequestBody ExpressionProcessForm expressionProcessForm) {
    return expressionServiceImpl.processExpression(expressionProcessForm);
  }

  @Override
  @PostMapping(PATH_EVALUATE_EXPRESSION)
  public String evaluateExpression(
      @PathParam(value = "id") String id, @RequestBody String jsonRaw) {
    return expressionServiceImpl.evaluateExpression(
        ExpressionEvaluationForm.builder().id(id).jsonRaw(jsonRaw).build());
  }
}
