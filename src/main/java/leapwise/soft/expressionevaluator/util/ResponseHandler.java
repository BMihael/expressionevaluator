package leapwise.soft.expressionevaluator.util;

import leapwise.soft.expressionevaluator.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseHandler {

  public static ResponseEntity<Object> generateErrorResponse(
      HttpStatus status, ErrorResponse errorResponse) {
    Map<String, Object> map = new HashMap<>();
    map.put("status", status.value());
    map.put("error", errorResponse);

    return new ResponseEntity<>(map, status);
  }
}
