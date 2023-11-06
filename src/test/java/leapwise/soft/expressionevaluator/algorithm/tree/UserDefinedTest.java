package leapwise.soft.expressionevaluator.algorithm.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDefinedTest {

    private static final String expression =
            "(customer.firstName == \"JOHN\" && customer.salary < 100) OR (customer.address != null && customer.address.city == \"Washington\")";

    private static final String expressionPopulated =
            "(\"JOHN\" == \"JOHN\" && 99 < 100) OR (X != null && \"Chicago\" == \"Washington\")";

    @Test
    public void expression() {
        TreeProvider.provideExpression(expressionPopulated);
        assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
    }
}
