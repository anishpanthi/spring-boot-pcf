package com.app.springboot.pcf.exception;

import com.app.springboot.pcf.dto.CrudResponseDto;
import com.app.springboot.pcf.util.Constants;
import com.app.springboot.pcf.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Anish Panthi
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(generateErrorResponse(exception), HttpStatus.OK);
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<?> handleDataAlreadyExistsException(DataAlreadyExistsException exception) {
        return new ResponseEntity<>(generateErrorResponse(exception), HttpStatus.OK);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException exception) {
        return new ResponseEntity<>(generateErrorResponse(exception), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllOtherException(Exception exception) {
        return new ResponseEntity<>(generateErrorResponse(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Object generateErrorResponse(Exception exception) {
        final CrudResponseDto crudResponseDto = new CrudResponseDto();
        crudResponseDto.setTimeStamp(DateUtil.getLocalDateNow());
        crudResponseDto.setStatus(Constants.ERROR);
        crudResponseDto.setMessage(exception.getMessage());
        return crudResponseDto;
    }
}
