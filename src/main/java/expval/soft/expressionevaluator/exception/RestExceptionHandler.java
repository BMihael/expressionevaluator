package expval.soft.expressionevaluator.exception;

import expval.soft.expressionevaluator.model.response.ErrorResponse;
import expval.soft.expressionevaluator.exception.algorithm.stack.HigherPrecedenceOperatorFound;
import expval.soft.expressionevaluator.exception.algorithm.tree.EmptyExpressionException;
import expval.soft.expressionevaluator.exception.algorithm.tree.NoLogicalExpressionException;
import expval.soft.expressionevaluator.exception.algorithm.tree.NoTreeGenerated;
import expval.soft.expressionevaluator.exception.algorithm.tree.NonComparableValuesException;
import expval.soft.expressionevaluator.model.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
  public static final String NO_REQUEST_RESOURCE_FOUND = "The requested resource was not found: ";

  private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

  @ExceptionHandler
  public ResponseEntity<Object> handleNameNotValidException(NameNotValidException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleValueNotValidException(ValueNotValidException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> noHandlerFoundExistException(NoHandlerFoundException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.NOT_FOUND,
        ErrorResponse.builder()
            .message(NO_REQUEST_RESOURCE_FOUND + exception.getRequestURL())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleExpressionWithIdDoesNotExistException(
      ExpressionWithIdDoesNotExistException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.NOT_FOUND,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleExpressionWithGivenNameAlreadyExistsException(
      ExpressionWithGivenNameAlreadyExistsException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleNonComparableValuesException(
      NonComparableValuesException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleCriticalException(CriticalException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleMultipleArgumentsExceptionException(
      MultipleArgumentsException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleNonNumericStringExceptionException(
      NonNumericStringException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleHigherPrecedenceOperatorFoundException(
      HigherPrecedenceOperatorFound exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleEmptyExpressionExceptionException(
      EmptyExpressionException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleNoLogicalExpressionExceptionException(
      NoLogicalExpressionException exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleNoTreeGeneratedException(NoTreeGenerated exception) {
    logger.error(exception.getMessage(), exception);
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }
}
