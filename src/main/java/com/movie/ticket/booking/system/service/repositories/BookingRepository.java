package com.movie.ticket.booking.system.service.repositories;

import com.movie.ticket.booking.system.service.entities.BookingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRepository extends CrudRepository<BookingEntity, UUID> {
}
