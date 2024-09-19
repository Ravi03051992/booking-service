package com.movie.ticket.booking.system.service.services;

import com.movie.ticket.booking.system.service.dtos.BookingDto;
import com.movie.ticket.booking.system.service.dtos.ResponseDto;

public interface BookingService {

    public ResponseDto createBooking(BookingDto bookingDto);

}
