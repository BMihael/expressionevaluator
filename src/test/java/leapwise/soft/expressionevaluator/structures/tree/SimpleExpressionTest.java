package leapwise.soft.expressionevaluator.structures.tree;

import leapwise.soft.expressionevaluator.ExpressionResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleExpressionTest {

  private static final String expression1 = "(1==1)";
  private static final String expression2 = "(10>5)";
  private static final String expression3 = "(3 > 1 && 5 < 10)";
  private static final String expression4 = "(6 == 6 && 4 != 7)";
  private static final String expression5 = "(8 < 12 && 9 == 9)";
  private static final String expression6 = "(11 < 5 && 13 != 7)";
  private static final String expression7 = "(15 == 15 && 20 != 25)";
  private static final String expression8 = "(4 < 2 or 6 > 12) or (3 != 3)";
  private static final String expression9 = "(7 == 7 && 5 != 8) && (10 > 11)";
  private static final String expression10 = "(9 < 15 && 8 == 8) or (12 != 12)";
  private static final String expression11 = "(15 > 8 && 14 != 9) or (30 == 365)";
  private static final String expression12 = "(20 == 20 && 35 != 40) or (50 < 60)";
  private static final String expression13 = "(3 > 7 && 9 < 15) or (2 == 1)";
  private static final String expression14 = "(6 == 4 && 5 != 5) or (8 > 7)";
  private static final String expression15 = "(10 < 9 && 11 == 11) or (12 != 12)";

  @Test
  public void expression1() {
    TreeProvider.provideExpression(expression1);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression2() {
    TreeProvider.provideExpression(expression2);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression3() {
    TreeProvider.provideExpression(expression3);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression4() {
    TreeProvider.provideExpression(expression4);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression5() {
    TreeProvider.provideExpression(expression5);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression6() {
    TreeProvider.provideExpression(expression6);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression7() {
    TreeProvider.provideExpression(expression7);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression8() {
    TreeProvider.provideExpression(expression8);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression9() {
    TreeProvider.provideExpression(expression9);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression10() {
    TreeProvider.provideExpression(expression10);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression11() {
    TreeProvider.provideExpression(expression11);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression12() {
    TreeProvider.provideExpression(expression12);
    assertEquals(ExpressionResult.TRUE.toString(), TreeProvider.printResult());
  }

  @Test
  public void expression13() {
    TreeProvider.provideExpression(expression13);
    assertEquals(ExpressionResult.FALSE.toString(), TreeProvider.printResult());
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
}
