package by.vitalylobatsevich.courser.http.controller;

import io.vavr.collection.List;
import io.vavr.collection.Map;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(final MethodArgumentNotValidException exception) {
        return List.ofAll(exception.getBindingResult().getAllErrors())
                .map(error ->  (FieldError) error)
                .toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage);
    }

}
