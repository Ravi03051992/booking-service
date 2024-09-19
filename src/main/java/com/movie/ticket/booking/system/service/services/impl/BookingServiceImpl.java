package com.movie.ticket.booking.system.service.services.impl;


import com.movie.ticket.booking.system.service.brokers.PaymentServiceBroker;
import com.movie.ticket.booking.system.service.dtos.BookingDto;
import com.movie.ticket.booking.system.service.dtos.ResponseDto;
import com.movie.ticket.booking.system.service.entities.BookingEntity;
import com.movie.ticket.booking.system.service.enums.BookingStatus;
import com.movie.ticket.booking.system.service.repositories.BookingRepository;
import com.movie.ticket.booking.system.service.services.BookingService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentServiceBroker paymentService;

    @Override
    public ResponseDto createBooking(BookingDto bookingDto) {
        log.info("Entered into BookingServiceimpl with createBooking Json Request Data: "+bookingDto);
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setUserId(bookingDto.getUserId());
        bookingEntity.setMovieId(bookingDto.getMovieId());
        bookingEntity.setSeatsSelected(bookingDto.getSeatsSelected());
        bookingEntity.setShowDate(bookingDto.getShowDate());
        bookingEntity.setShowTime(bookingDto.getShowTime());
        bookingEntity.setBookingStatus(BookingStatus.PENDING);
        bookingEntity.setBookingAmount(bookingDto.getBookingAmount());



       this.bookingRepository.save(bookingEntity); // create booking with booking status as pending
       bookingDto.setBookingId(bookingEntity.getBookingId());
       bookingDto.setBookingStatus(bookingEntity.getBookingStatus());


// call to payment service

  BookingDto bookingDtoPaymentResponse= paymentService.makePayment(bookingDto);
  bookingEntity.setBookingStatus(bookingDtoPaymentResponse.getBookingStatus());

      return ResponseDto.builder()
              .bookingDto(BookingDto.builder()
                      .bookingId(bookingEntity.getBookingId())
                      .userId(bookingEntity.getUserId())
              .movieId(bookingEntity.getMovieId())
              .seatsSelected(bookingEntity.getSeatsSelected())
              .showDate(bookingEntity.getShowDate())
              .showTime(bookingEntity.getShowTime())
              .bookingStatus(bookingDtoPaymentResponse.getBookingStatus())
              .bookingAmount(bookingEntity.getBookingAmount())
               .build())
              .build();

    }
}
