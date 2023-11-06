package leapwise.soft.expressionevaluator.util;

public interface ExpressionStripper {

  static String stripField(String value, ExpressionStripperValues toStripWith) {
    return value.replaceFirst(toStripWith.toString(), "");
  }
}
