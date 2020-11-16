package com.golearn.controller;

import java.nio.file.AccessDeniedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 400 에러
	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> BadRequestException(final RuntimeException error) {
		return ResponseEntity.badRequest().body("서버가 요청의 구문을 인식하지 못했습니다.");
	}
	
	// 401 에러
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity handleAccessDeniedException(final AccessDeniedException error) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증이 필요한 요청입니다.");
    }
    
    // 500 에러
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception error) {
        return new ResponseEntity<>("서버에 오류가 발생하여 요청을 수행할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
