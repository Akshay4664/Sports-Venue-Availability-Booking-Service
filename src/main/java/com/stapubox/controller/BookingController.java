package com.stapubox.controller;

import com.stapubox.entity.Booking;
import com.stapubox.request.BookingRequest;
import com.stapubox.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking book(@RequestBody BookingRequest req) {
        return bookingService.bookSlot(req);
    }

    @PutMapping("/{id}/cancel")
    public void cancel(@PathVariable Long id) {
        bookingService.cancel(id);
    }
}

