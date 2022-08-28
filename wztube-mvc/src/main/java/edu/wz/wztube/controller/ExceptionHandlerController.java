package edu.wz.wztube.controller;

import edu.wz.wztube.exception.ErrorMessage;
import edu.wz.wztube.exception.VideoUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(value = {VideoUploadException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(VideoUploadException e) {
        ErrorMessage message = new ErrorMessage(e.getMessage());
        return ResponseEntity.badRequest().body(message);
    }
}
