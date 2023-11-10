package leapwise.soft.expressionevaluator.structures.tree;

import leapwise.soft.expressionevaluator.ExpressionResult;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringExpressionTest {

  private static final String expression1 = "(\"JOHN\" == \"JOHN\")";
  private static final String expression2 = "(\"JOHN\" == \"NOT_JOHN\")";

  private static final String expression3 = "(8 < 6 && 4 > 4) or (\"pear\" == \"grape\")";
  private static final String expression4 =
      "(\"dog\" != \"cat\" && \"red\" == \"blue\") or (null == null)";
  private static final String expression5 =
      "(3 > 2 or \"banana\" != \"banana\") && (null == \"kiwi\")";
  private static final String expression6 =
      "(10 == 10 && \"apple\" == \"apple\") or (null != null)";
  private static final String expression7 =
      "(\"cherry\" != \"cherry\" && 6 > 7) && (null == \"grape\")";
  private static final String expression8 =
      "(5 < 5 or \"apple\" == \"banana\") or (null != \"orange\")";
  private static final String expression9 = "(9 > 7 && 3 < 2) or (\"lemon\" != \"lemon\")";
  private static final String expression10 =
      "(\"cat\" == \"dog\" && \"blue\" == \"red\") && (null == \"apple\")";
  private static final String expression11 =
      "(2 == 2 or \"banana\" != \"banana\") or (null == null)";
  private static final String expression12 =
      "(\"pear\" != \"apple\" && 5 > 5) && (null != \"cherry\")";
  private static final String expression13 =
      "(6 < 8 or \"grape\" == \"grape\") or (null == \"pear\")";
  private static final String expression14 =
      "(7 > 7 && \"apple\" == \"apple\") or (null != \"banana\")";
  private static final String expression15 =
      "(\"dog\" != \"dog\" && \"red\" == \"blue\") && (null == null)";
  private static final String expression16 =
      "(7 < 8 or \"apple\" == \"apple\") && (null != \"orange\")";
  private static final String expression17 =
      "(\"apple\" == \"banana\" && 5 > 3) or (null == \"cherry\")";
  private static final String expression18 =
      "(((\"dog\" != \"cat\" && \"red\" == \"blue\") or (null == null)) == (2>1))";

  private static final String expression19 = "(null == customer.address)";
  private static final String expression20 = "(null != customer.address)";
  private static final String expression21 = "(customer.address == null)";
  private static final String expression22 = "(customer.address != null)";

  private static final String expression23 = "(customer == null)";
  private static final String expression24 = "(customer.notExists == null)";

  private static final String expression25 = "(customer != null)";
  private static final String expression26 = "(customer.notExists != null)";

  @Test
  public void expression1() {
    TreeProvider.provideExpression(expression1);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression2() {
    TreeProvider.provideExpression(expression2);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression3() {
    TreeProvider.provideExpression(expression3);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression4() {
    TreeProvider.provideExpression(expression4);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression5() {
    TreeProvider.provideExpression(expression5);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression6() {
    TreeProvider.provideExpression(expression6);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression7() {
    TreeProvider.provideExpression(expression7);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression8() {
    TreeProvider.provideExpression(expression8);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression9() {
    TreeProvider.provideExpression(expression9);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression10() {
    TreeProvider.provideExpression(expression10);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression11() {
    TreeProvider.provideExpression(expression11);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression12() {
    TreeProvider.provideExpression(expression12);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression13() {
    TreeProvider.provideExpression(expression13);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression14() {
    TreeProvider.provideExpression(expression14);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression15() {
    TreeProvider.provideExpression(expression15);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression16() {
    TreeProvider.provideExpression(expression16);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression17() {
    TreeProvider.provideExpression(expression17);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression18() {
    TreeProvider.provideExpression(expression18);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression19() throws JSONException {
    TreeProvider.provideExpression(expression19);
    TreeProvider.fillTreeHelper(buildJsonObject());
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression20() throws JSONException {
    TreeProvider.provideExpression(expression20);
    TreeProvider.fillTreeHelper(buildJsonObject());
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression21() throws JSONException {
    TreeProvider.provideExpression(expression21);
    TreeProvider.fillTreeHelper(buildJsonObject());
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression22() throws JSONException {
    TreeProvider.provideExpression(expression22);
    TreeProvider.fillTreeHelper(buildJsonObject());
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression23() throws JSONException {
    TreeProvider.provideExpression(expression23);
    TreeProvider.fillTreeHelper(buildJsonObject());
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression24() throws JSONException {
    TreeProvider.provideExpression(expression24);
    TreeProvider.fillTreeHelper(buildJsonObject());
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression25() throws JSONException {
    TreeProvider.provideExpression(expression25);
    TreeProvider.fillTreeHelper(buildJsonObject());
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression26() throws JSONException {
    TreeProvider.provideExpression(expression26);
    TreeProvider.fillTreeHelper(buildJsonObject());
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  private JSONObject buildJsonObject() throws JSONException {
    JSONObject jsonObjectInnerInner = new JSONObject();
    jsonObjectInnerInner.put("city", "Chicago");
    jsonObjectInnerInner.put("zipCode", 1234);
    jsonObjectInnerInner.put("street", "56th");
    jsonObjectInnerInner.put("houseNumber",2345);

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
