package com.esl.customerprofilingservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice(basePackages = {"com.esl.customerprofilingservice.controller"})
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        log.error("Exception stacktrace: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse("91", "Error Occurred", details), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleEntryNotFoundException(RecordNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Entry not found");

        log.info("{}", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
