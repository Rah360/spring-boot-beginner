package come.rahullearnings.exceptions;

import come.rahullearnings.dto.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorModel> handleInvalidId(IllegalArgumentException ex) {
        ErrorModel error = new ErrorModel(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // This extracts the specific error message (e.g., "name cannot be null")
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("message", "Invalid Input: " + errorMessage);
        body.put("errorCode", 400);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class) // Match this...
    public ResponseEntity<Object> handleIdNotFound(IllegalStateException ex) { // ...to this!

        // ex.getMessage() is just a String like "id not found"
        String errorMessage = ex.getMessage();

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("message", "Error: " + errorMessage);
        body.put("errorCode", 404); // Using 404 since the ID is missing

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handleGlobalError(Exception ex) {
        ex.printStackTrace();
        ErrorModel error = new ErrorModel("An unexpected error occurred", 500);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
