package com.movie.ticket.booking.system.service.apis;

import com.movie.ticket.booking.system.service.dtos.BookingDto;
import com.movie.ticket.booking.system.service.dtos.ResponseDto;
import com.movie.ticket.booking.system.service.services.BookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bookings")
@Slf4j
public class BookingApi {


    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<ResponseDto> createBooking(@Valid @RequestBody BookingDto bookingDto){
        log.info("Entered into Booking Api with JSON request: "+bookingDto);
        ResponseDto responseDto= this.bookingService.createBooking(bookingDto);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED );
    }
}
