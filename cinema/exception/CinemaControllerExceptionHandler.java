package cinema.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CinemaControllerExceptionHandler {

  private static final String ERROR_MSG_KEY = "error";

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<Map<String, String>> handleNotFound() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(ERROR_MSG_KEY, "The number of a row or a column is out of bounds!"));
  }

  @ExceptionHandler({IllegalStateException.class})
  public ResponseEntity<Map<String, String>> handleAlreadyPurchased() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(ERROR_MSG_KEY, "The ticket has been already purchased!"));
  }

  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<Map<String, String>> handleNotFoundByToken() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(ERROR_MSG_KEY, "Wrong token!"));
  }

  @ExceptionHandler({NullPointerException.class})
  public ResponseEntity<Map<String, String>> handleNoPassword() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(ERROR_MSG_KEY, "The password is wrong!"));
  }

}
