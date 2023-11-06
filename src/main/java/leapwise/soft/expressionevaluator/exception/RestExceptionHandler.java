package leapwise.soft.expressionevaluator.exception;

import leapwise.soft.expressionevaluator.model.response.ErrorResponse;
import leapwise.soft.expressionevaluator.util.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

  public static final String NO_REQUEST_RESOURCE_FOUND = "The requested resource was not found: ";

  @ExceptionHandler
  public ResponseEntity<Object> handleNameNotValidException(NameNotValidException exception) {
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleValueNotValidException(ValueNotValidException exception) {
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }

  @ExceptionHandler
  public ResponseEntity<Object> noHandlerFoundExistException(NoHandlerFoundException exception) {
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
    return ResponseHandler.generateErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorResponse.builder()
            .message(exception.getMessage())
            .timestamp(System.currentTimeMillis())
            .build());
  }
}
