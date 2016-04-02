package com.cver.team.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Dimitar on 4/2/2016.
 */
@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler
    @ResponseBody
    public void handle(Exception e) {
        e.printStackTrace();
    }
}
