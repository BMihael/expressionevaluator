package leapwise.soft.expressionevaluator.controller.impl;

import leapwise.soft.expressionevaluator.controller.ExpressionController;
import leapwise.soft.expressionevaluator.model.form.ExpressionEvaluationForm;
import leapwise.soft.expressionevaluator.model.form.ExpressionProcessForm;
import leapwise.soft.expressionevaluator.service.impl.ExpressionServiceImpl;
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
    @PostMapping("/expression")
    public String processExpression(@Valid @RequestBody ExpressionProcessForm expressionProcessForm) {
        return expressionServiceImpl.processExpression(expressionProcessForm); // odvje napraviti mapiranje iz form u dto
    }

    @Override
    @PostMapping("/evaluate")
    public String evaluateExpression(@PathParam(value = "id") String id, @RequestBody String jsonRaw) {
        return expressionServiceImpl.evaluateExpression(ExpressionEvaluationForm.builder().id(id).jsonRaw(jsonRaw).build());
    }
}
