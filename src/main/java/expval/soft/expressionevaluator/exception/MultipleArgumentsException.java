package expval.soft.expressionevaluator.exception;

import java.util.ArrayList;
import java.util.List;

import static expval.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS;

public abstract class MultipleArgumentsException extends RuntimeException {

  private List<String> argumentsArray;

  public MultipleArgumentsException(String message) {
    super(message);
  }

  public MultipleArgumentsException(String message, int maxNumberOfArguments, String... args) {
    super(message);
    if (args.length > maxNumberOfArguments) {
      throw new RuntimeException(INVALID_NUMBER_OF_ARGUMENTS);
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
