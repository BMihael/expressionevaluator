package leapwise.soft.expressionevaluator.algorithm.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackTest {

  private static final String expression1 = " (null != \"orange\")";

  @Test
  public void setExpression1() {
    int indexOfParent = StackHelper.getIndexOfParentExpression(expression1);
    Assertions.assertEquals(7, indexOfParent);
  }
}
