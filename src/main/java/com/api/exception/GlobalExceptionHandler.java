package com.api.exception;

import com.api.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //custom exception handler
    public ResponseEntity<ErrorDto> resourceNotFound(
            ResourceNotFoundException r,
            WebRequest request // from which url request was made
    ){
        ErrorDto error = new ErrorDto(r.getMessage(),new Date(),request.getDescription(true));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler({Exception.class})
    ResponseEntity<String> handleGlobalException( Exception e){ // handle global exceptions
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
