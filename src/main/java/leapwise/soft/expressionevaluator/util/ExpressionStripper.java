package leapwise.soft.expressionevaluator.util;

public class ExpressionStripper {
    public static String stripField(String field, String toStripWith){
        return field.replaceFirst(toStripWith, "");
    }
}
