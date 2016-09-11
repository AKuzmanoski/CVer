package com.cver.team.web;

import com.cver.team.web.ResponseExceptions.OperationNotAuthorizedException;
import com.cver.team.web.ResponseExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dimitar on 4/2/2016.
 */
@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler
    @ResponseBody
    public void handle(Exception e, HttpServletResponse response) throws IOException {
        if (e instanceof ResourceNotFoundException)
            response.sendError(HttpStatus.NOT_FOUND.value());
        else if (e instanceof OperationNotAuthorizedException)
            response.sendError(HttpStatus.FORBIDDEN.value());
        else e.printStackTrace();
    }
}
