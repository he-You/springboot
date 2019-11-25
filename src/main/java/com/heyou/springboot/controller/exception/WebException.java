package com.heyou.springboot.controller.exception;

import com.heyou.springboot.entity.base.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author heyou
 * @date 2019-11-26 00:17
 */
@ControllerAdvice
public class WebException {
    private final Result result = new Result();

    private static final Logger loggger = LoggerFactory.getLogger(WebException.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result systemException(Exception e) {
        loggger.error("System error");
        return result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result methodNotSupported(NoHandlerFoundException e) {
        loggger.error("method not found : 404");
        return result.error(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result methodNotSupported(HttpRequestMethodNotSupportedException e) {
        loggger.error("method not support : 405");
        return result.error(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Result mediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
        loggger.error("media type not support : 415");
        return result.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Result mediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException e) {
        loggger.error("media type not acceptable : 406");
        return result.error(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
    }
}
