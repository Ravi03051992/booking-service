package com.movie.ticket.booking.system.service.entities;


import com.movie.ticket.booking.system.service.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id")
    private UUID bookingId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "movie_id")
    private Integer movieId;

    @ElementCollection
    private List<String> seatsSelected;

    @Column(name = "local_date")
    private LocalDate showDate;

    @Column(name = "local_time")
    private LocalTime showTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(name = "booking_amount")
    private Double bookingAmount;
}
