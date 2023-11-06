package leapwise.soft.expressionevaluator.exception;

import java.util.ArrayList;
import java.util.List;

public abstract class MultipleArgumentsException extends RuntimeException {

  private List<String> argumentsArray;

  public MultipleArgumentsException(String message) {
    super(message);
  }

  public MultipleArgumentsException(String message, int maxNumberOfArguments, String... args) {
    super(message);
    if (args.length > maxNumberOfArguments) {
      throw new RuntimeException("Previse argumenata");
    }
    argumentsArray = new ArrayList<>();
    for (int i = 0; i < maxNumberOfArguments; i++) {
      argumentsArray.add(args[i]);
    }
  }

  public List<String> getArguments() {
    return this.argumentsArray;
  }
}
