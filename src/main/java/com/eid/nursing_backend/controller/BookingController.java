package com.eid.nursing_backend.controller;

import com.eid.nursing_backend.model.Booking;
import com.eid.nursing_backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/{whatsappNumberId}")
    public Booking createBooking(@PathVariable Long whatsappNumberId, @RequestBody Booking booking) {
        return bookingService.createBooking(booking, whatsappNumberId);
    }
}
