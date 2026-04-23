package com.smart.contact.exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponse> HandleNotFound(ResourceNotFound exception, HttpServletRequest request){
        ErrorResponse error=new ErrorResponse(System.currentTimeMillis(),
                404,
                "Not Found",
                exception.getMessage(),
                request.getRequestURI());
        return new  ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex,HttpServletRequest request){
        ErrorResponse errorResponse=new ErrorResponse(System.currentTimeMillis(),
                500,
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handlivalidation(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return new  ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
