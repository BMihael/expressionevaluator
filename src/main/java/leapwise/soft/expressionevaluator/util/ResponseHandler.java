package leapwise.soft.expressionevaluator.util;

import leapwise.soft.expressionevaluator.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseHandler {

    public static ResponseEntity<Object> generateErrorResponse(HttpStatus status, ErrorResponse errorResponse) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("error", errorResponse);

        return new ResponseEntity<>(map, status);
    }
/*
    public static ResponseEntity<Object> generateResponse(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK.value());
        map.put("data", data);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }*/
}
