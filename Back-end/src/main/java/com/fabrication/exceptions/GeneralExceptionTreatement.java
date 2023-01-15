package com.fabrication.exceptions;

import com.fabrication.utils.ErrorBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GeneralExceptionTreatement{

    /**
     * This function will be called whenever a ResourceNotFoundException is thrown
     *
     * @param ex The exception object.
     * @param request The request that triggered the exception.
     * @return A ResponseEntity with a message and a status code.
     */
    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<ErrorBody> customExceptionController(
            RuntimeException ex, WebRequest request) {
        ErrorBody errorBody = new ErrorBody(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.toString(),
                ((ServletWebRequest)request).getRequest().getRequestURI()
        );
        return new ResponseEntity<>(errorBody,HttpStatus.NOT_FOUND);
    }
}
