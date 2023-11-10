package leapwise.soft.expressionevaluator.structures.tree;

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
  private static final String expressionWithAddressNull =
          "(customer.firstName == \"JOHN\" && customer.salary < 100) && (customer.address != null or customer.address.city != null)";

  private static final String emptyExpression = "";
  private static final String noLogicalExpression = "((";
  private static final String expressionWithoutParentasis = "10>5";
  private static final String nonComparableValues = "\"JOHN\" == 2";

  private static final String field_bothOperands_DoesNotExistInJSONDataset =
      "(customer.firstName == customer.address.city)";

  private static final String field_addressCity_DoesNotExistInJSONDataset =
      "(customer.firstName == customer.address.city)";

  private static final String oneExpressionElementIsNumberOtherIsString = "(99 == \"JOHN\")";

  private static final String operatorMismatchTest1 = "1>null";
  private static final String operatorMismatchTest2 = "null>1";
  private static final String operatorMismatchTest3 = "1<null";
  private static final String operatorMismatchTest4 = "null<1";

  private static final String expressionVariableIsNull = "(customer.address == null)";
  private static final String expressionVariableIsNotNull = "(customer.address != null)";

  private static final String expressionWithMoreThanOnLogicalOperatorWithoutParentheses =
      "(10>5) or (11>5) && (12>5)";

  @Test
  public void expression() throws JSONException {
    TreeProvider.provideExpression(expression);
    TreeProvider.fillTreeHelper(buildJsonObject());
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

  @Test
  public void operatorMismatchTest1() {
    NonComparableValuesException exception =
        assertThrows(
            NonComparableValuesException.class,
            () -> {
              TreeProvider.provideExpression(operatorMismatchTest1);
              TreeProvider.printResult();
            });

    List<String> exceptionArguments = exception.getArguments();
    assertEquals(exceptionArguments.get(0), "1");
    assertEquals(exceptionArguments.get(1), "null");
  }

  @Test
  public void operatorMismatchTest2() {
    NonComparableValuesException exception =
        assertThrows(
            NonComparableValuesException.class,
            () -> {
              TreeProvider.provideExpression(operatorMismatchTest2);
              TreeProvider.printResult();
            });

    List<String> exceptionArguments = exception.getArguments();
    assertEquals(exceptionArguments.get(0), "null");
    assertEquals(exceptionArguments.get(1), "1");
  }

  @Test
  public void operatorMismatchTest3() {
    NonComparableValuesException exception =
        assertThrows(
            NonComparableValuesException.class,
            () -> {
              TreeProvider.provideExpression(operatorMismatchTest3);
              TreeProvider.printResult();
            });

    List<String> exceptionArguments = exception.getArguments();
    assertEquals(exceptionArguments.get(0), "1");
    assertEquals(exceptionArguments.get(1), "null");
  }

  @Test
  public void operatorMismatchTest4() {
    NonComparableValuesException exception =
        assertThrows(
            NonComparableValuesException.class,
            () -> {
              TreeProvider.provideExpression(operatorMismatchTest4);
              TreeProvider.printResult();
            });

    List<String> exceptionArguments = exception.getArguments();
    assertEquals(exceptionArguments.get(0), "null");
    assertEquals(exceptionArguments.get(1), "1");
  }

  @Test
  public void expressionVariableIsNull() throws JSONException {
    JSONObject jsonObjectInner = new JSONObject();
    jsonObjectInner.put("address", JSONObject.NULL);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("customer", jsonObjectInner);

    TreeProvider.provideExpression(expressionVariableIsNull);
    TreeProvider.fillTreeHelper(jsonObject);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expressionVariableIsNotNull() throws JSONException {
    TreeProvider.provideExpression(expressionVariableIsNotNull);
    TreeProvider.fillTreeHelper(buildJsonObject());
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expressionWithMoreThanOnLogicalOperatorWithoutParenthases() {
    TreeProvider.provideExpression(expressionWithMoreThanOnLogicalOperatorWithoutParentheses);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expressionWithAddressNull() throws JSONException {
    TreeProvider.provideExpression(expressionWithAddressNull);

    JSONObject jsonObjectInner = new JSONObject();
    jsonObjectInner.put("address", JSONObject.NULL);
    jsonObjectInner.put("firstName", "JOHN");
    jsonObjectInner.put("lastName", "DOE");
    jsonObjectInner.put("salary", 99);
    jsonObjectInner.put("type", "BUSINESS");

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("customer", jsonObjectInner);

    TreeProvider.fillTreeHelper(jsonObject);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  private JSONObject buildJsonObject() throws JSONException {
    JSONObject jsonObjectInnerInner = new JSONObject();
    jsonObjectInnerInner.put("city", "Chicago");
    jsonObjectInnerInner.put("zipCode", 1234);
    jsonObjectInnerInner.put("street", "56th");
    jsonObjectInnerInner.put("houseNumber", 2345);

    JSONObject jsonObjectInner = new JSONObject();
    jsonObjectInner.put("address", jsonObjectInnerInner);
    jsonObjectInner.put("firstName", "JOHN");
    jsonObjectInner.put("lastName", "DOE");
    jsonObjectInner.put("salary", 99);
    jsonObjectInner.put("type", "BUSINESS");

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("customer", jsonObjectInner);
    return jsonObject;
  }
}
