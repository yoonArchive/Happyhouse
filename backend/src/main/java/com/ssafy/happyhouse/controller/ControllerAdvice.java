package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.common.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public Message BadRequestException(Exception exception) {
        Message message = new Message(exception.getMessage());
        log.info(message.getMessage());
        return message;
    }
}
