package com.movie.ticket.booking.system.service.handlers;


import com.movie.ticket.booking.system.service.dtos.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class BookingApiHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        log.info("Entered into BookingApiHandler with createBooking getting exception: "+exception.getMessage());
        List<ObjectError> errors =exception.getBindingResult().getAllErrors();
        ResponseDto responseDto = ResponseDto.builder()
                .errorDescription(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorMessage(errors
                        .stream()
                       // .map(objectError -> objectError.getDefaultMessage())
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.toList())
                )
                .build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.BAD_REQUEST);

    }
}
