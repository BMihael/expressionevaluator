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
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static leapwise.soft.expressionevaluator.validation.ExpressionNameFieldValidator.NAME_FIELD_PREFIX;
import static leapwise.soft.expressionevaluator.validation.ExpressionNameFieldValidator.VALUE_FIELD_PREFIX;

@Service
public class ExpressionServiceImpl implements ExpressionService {

    private final ExpressionRepository expressionRepository;

    public ExpressionServiceImpl(ExpressionRepository expressionRepository) {
        this.expressionRepository = expressionRepository;
    }

    @Override
    public String processExpression(ExpressionProcessForm expressionProcessForm) {
        String name = ExpressionStripper.stripField(expressionProcessForm.getName(), NAME_FIELD_PREFIX);
        checkIfExpressionNameExists(name);

        String value = ExpressionStripper.stripField(expressionProcessForm.getValue(), VALUE_FIELD_PREFIX);

        Expression e = expressionRepository.save(
                Expression.builder().
                        name(name).
                        expressionValue(value)
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
        String result = TreeProvider.printResult();
        return result;
    }

    private void checkIfExpressionNameExists(String name) {
        Expression exp = expressionRepository.nameExists(name);
        if (exp != null) {
            throw new ExpressionWithGivenNameAlreadyExistsException("Exception with given name already exists in database");
        }
    }

    private Expression findExpressionByIdentifier(String id) {
        Expression exp = expressionRepository.findExpression(id);
        if (exp == null) {
            throw new ExpressionWithIdDoesNotExistException("Exception with given id does not exist");
        }
        return exp;
    }
}
