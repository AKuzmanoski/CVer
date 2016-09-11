package com.cver.team.web.ResponseExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by PC on 06/09/2016.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class OperationNotAuthorizedException extends RuntimeException {
}
