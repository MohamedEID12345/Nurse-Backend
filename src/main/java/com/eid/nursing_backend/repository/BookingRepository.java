package com.eid.nursing_backend.repository;


import com.eid.nursing_backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}