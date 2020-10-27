package com.golearn.controller;

import com.golearn.exception.UnAuthorizationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler({
            RuntimeException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity handleBadRequestException(final RuntimeException ex) {
        log.warn("error", ex);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    // 401
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity handleAccessDeniedException(final AccessDeniedException ex) {
        log.warn("error", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
    @ExceptionHandler({UnAuthorizationException.class})
    public ResponseEntity handleUnAuthorizationException(final UnAuthorizationException ex){
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
    // 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex) {
        log.info(ex.getClass().getName());
        log.error("error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
