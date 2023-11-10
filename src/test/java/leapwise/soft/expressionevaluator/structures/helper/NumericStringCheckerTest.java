package leapwise.soft.expressionevaluator.structures.helper;

import leapwise.soft.expressionevaluator.structures.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.structures.tree.nodes.number.NumberNode;
import leapwise.soft.expressionevaluator.exception.NonNumericStringException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumericStringCheckerTest {

  private static final String NUMBER_STRING = "100";
  private static final Double NUMBER = 100.0;
  private static final String STRING_KEYWORD = "word";

  @Test
  void checkIfNumeric_PassingTest() {
    double number = NumericStringCheckerImpl.checkIfNumeric(NUMBER_STRING);
    Assertions.assertEquals(NUMBER, number);
  }

  @Test
  void checkIfNumeric_FailedTest() {
    NonNumericStringException exception =
        assertThrows(
            NonNumericStringException.class,
            () -> {
              NumericStringCheckerImpl.checkIfNumeric(STRING_KEYWORD);
            });

    Assertions.assertEquals(exception.getArguments().get(0), STRING_KEYWORD);
  }

  @Test
  void areNumericEquals_PassingTest() {
    Assertions.assertTrue(
        NumericStringCheckerImpl.areNumericEquals(
            new NumberNode(NUMBER_STRING, NodeType.NUMERIC_NODE),
            new NumberNode(NUMBER_STRING, NodeType.NUMERIC_NODE)));
  }

  @Test
  void careNumericEqualsThrowsException() {
    assertThrows(
        NonNumericStringException.class,
        () -> {
          NumericStringCheckerImpl.areNumericEquals(
              new NumberNode(NUMBER_STRING, NodeType.NUMERIC_NODE),
              new NumberNode(STRING_KEYWORD, NodeType.STRING_NODE));
        });
  }

  @Test
  public void checkIfNumericReturnBoolean_ReturnsTrue() {
    assertTrue(NumericStringCheckerImpl.checkIfNumericReturnBoolean(NUMBER_STRING));
  }

  @Test
  public void checkIfNumericReturnBoolean_ReturnsFalse() {
    assertFalse(NumericStringCheckerImpl.checkIfNumericReturnBoolean(STRING_KEYWORD));
  }
}
