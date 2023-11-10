package leapwise.soft.expressionevaluator.structures.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackTest {

  private static final String expression1 = "(1 > 10 && 2 < 20)";
  private static final String expression2 = "(3 == 4 or 5 > 18)";
  private static final String expression3 = "(6 < 100 && 7 > 50)";
  private static final String expression4 = "((8 == 9 or 10 == 11) && (12 == 13))";
  private static final String expression5 = "((14 == 15) && (16 > 0))";
  private static final String expression6 = "(((17 > 2) == (0<0)) or (18 > 10))";
  private static final String expression7 = "(19 > 0 && (20 > 10 or 21 > 2))";
  private static final String expression8 = "((22 == 23) or (((17 > 2) == (0<0)) or (18 > 10)))";
  private static final String expression9 = "((0 < 27) or (26>1))";
  private static final String expression10 = " (null != \"orange\")";
  private static final String expression11 = " (null > \"orange\")";
  private static final String expression12 = "(1 == 5)";
  private static final String expression13 =
      "((((22 == 23) or (((17 > 2) == (0<0)) or (18 > 10)))) or (((22 == 23) or (((17 > 2) == (0<0)) or (18 > 10))))) or (((((22 == 23) or (((17 > 2) == (0<0)) or (18 > 10)))) or (((22 == 23) or (((17 > 2) == (0<0)) or (18 > 10))))))";
  private static final String expression14 =
      "(2==2) && ((((22 == 23) or (((17 > 2) == (0<0)) or (18 > 10)))) or (((22 == 23) or (((17 > 2) == (0<0)) or (18 > 10))))) or (((((22 == 23) or (((17 > 2) == (0<0)) or (18 > 10)))) or (((22 == 23) or (((17 > 2) == (0<0)) or (18 > 10))))))";

  @Test
  public void expression1() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression1);
    Assertions.assertEquals(8, indexOfParent);
  }

  @Test
  public void expression2() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression2);
    Assertions.assertEquals(8, indexOfParent);
  }

  @Test
  public void expression3() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression3);
    Assertions.assertEquals(9, indexOfParent);
  }

  @Test
  public void expression4() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression4);
    Assertions.assertEquals(22, indexOfParent);
  }

  @Test
  public void expression5() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression5);
    Assertions.assertEquals(12, indexOfParent);
  }

  @Test
  public void expression6() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression6);
    Assertions.assertEquals(21, indexOfParent);
  }

  @Test
  public void expression7() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression7);
    Assertions.assertEquals(8, indexOfParent);
  }

  @Test
  public void expression8() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression8);
    Assertions.assertEquals(12, indexOfParent);
  }

  @Test
  public void expression9() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression9);
    Assertions.assertEquals(10, indexOfParent);
  }

  @Test
  public void expression10() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression10);
    Assertions.assertEquals(7, indexOfParent);
  }

  @Test
  public void expression11() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression11);
    Assertions.assertEquals(7, indexOfParent);
  }

  @Test
  public void expression12() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression12);
    Assertions.assertEquals(3, indexOfParent);
  }

  @Test
  public void expression13() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression13);
    Assertions.assertEquals(111, indexOfParent);
  }

  @Test
  public void expression14() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression14);
    Assertions.assertEquals(7, indexOfParent);
  }
}
