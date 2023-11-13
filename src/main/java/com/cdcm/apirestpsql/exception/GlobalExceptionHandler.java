package com.cdcm.apirestpsql.exception;

import com.cdcm.apirestpsql.exception.custom.CustomErrorException;
import com.cdcm.apirestpsql.exception.custom.ItemNotFoundException;
import com.cdcm.apirestpsql.model.payload.CustomError;
import com.cdcm.apirestpsql.model.payload.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception,
            WebRequest webRequest) {

        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult()
        .getAllErrors()
        .forEach((objectError -> {
            String key = ((FieldError) objectError).getField();
            String value = objectError.getDefaultMessage();
            errorMap.put(key, value);
        }));

        CustomResponse customResponse = CustomResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(true)
                .errorData(CustomError.builder()
                        .message("Invalid arguments")
                        .code(HttpStatus.BAD_REQUEST.name())
                        .details(errorMap)
                        .build())
                .data(null)
                .build();
        return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<CustomResponse> customErrorExceptionHandler(
            CustomErrorException exception,
            WebRequest webRequest) {
        CustomResponse customResponse = CustomResponse.builder()
                .status(exception.getStatus().value())
                .error(true)
                .errorData(CustomError.builder()
                        .code(exception.getStatus().name())
                        .message(exception.getMessage())
                        .build())
                .data(null)
                .build();
        return new ResponseEntity<>(customResponse, exception.getStatus());
    }
}
