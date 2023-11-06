package leapwise.soft.expressionevaluator.algorithm.tree;

import leapwise.soft.expressionevaluator.ExpressionResult;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.EmptyExpressionException;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.NoLogicalExpressionException;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.NonComparableValuesException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDefinedTest {

  private static final String expression =
      "(customer.firstName == \"JOHN\" && customer.salary < 100) OR (customer.address != null && customer.address.city == \"Washington\")";
  private static final String expressionPopulated =
      "(\"JOHN\" == \"JOHN\" && 99 < 100) OR (X != null && \"Chicago\" == \"Washington\")";

  private static final String emptyExpression = "";
  private static final String noLogicalExpression = "((";
  private static final String expressionWithoutParentasis = "10>5";
  private static final String nonComparableValues = "\"JOHN\" == 2";

  private static final String field_bothOperands_DoesNotExistInJSONDataset =
      "(customer.firstName == customer.address.city)";

  private static final String field_addressCity_DoesNotExistInJSONDataset =
      "(customer.firstName == customer.address.city)";

  private static final String oneExpressionElementIsNumberOtherIsString = "(99 == \"JOHN\")";

  @Test
  public void expression() {
    TreeProvider.provideExpression(expressionPopulated);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void emptyExpressionException() {
    assertThrows(
        EmptyExpressionException.class,
        () -> {
          TreeProvider.provideExpression(emptyExpression);
          TreeProvider.printResult();
        });
  }

  @Test
  public void noLogicalExpressionException() {
    assertThrows(
        NoLogicalExpressionException.class,
        () -> {
          TreeProvider.provideExpression(noLogicalExpression);
          TreeProvider.printResult();
        });
  }

  @Test
  public void withoutParentasis() {
    TreeProvider.provideExpression(expressionWithoutParentasis);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void nonComparableValues() {
    assertThrows(
        NonComparableValuesException.class,
        () -> {
          TreeProvider.provideExpression(nonComparableValues);
          TreeProvider.printResult();
        });
  }

  @Test
  public void field_City_DoesNotExistInJSONDataset() throws JSONException {
    JSONObject jsonObjectInner = new JSONObject();

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("customer", jsonObjectInner);

    TreeProvider.provideExpression(field_bothOperands_DoesNotExistInJSONDataset);
    TreeProvider.fillTreeHelper(jsonObject);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void field_addressCity_DoesNotExistInJSONDataset() throws JSONException {
    JSONObject jsonObjectInnerInner = new JSONObject();

    JSONObject jsonObjectInner = new JSONObject();
    jsonObjectInner.put("address", jsonObjectInnerInner);
    jsonObjectInner.put("firstName", "JOHN");

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("customer", jsonObjectInner);

    TreeProvider.provideExpression(field_addressCity_DoesNotExistInJSONDataset);
    TreeProvider.fillTreeHelper(jsonObject);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void oneExpressionElementIsNumberOtherIsString() {
    NonComparableValuesException exception =
        assertThrows(
            NonComparableValuesException.class,
            () -> {
              TreeProvider.provideExpression(oneExpressionElementIsNumberOtherIsString);
              TreeProvider.printResult();
            });

    List<String> exceptionArguments = exception.getArguments();
    assertEquals(exceptionArguments.get(0), "99");
    assertEquals(exceptionArguments.get(1), "\"JOHN\"");
  }
}
