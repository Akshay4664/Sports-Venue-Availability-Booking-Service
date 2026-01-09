package com.stapubox.service;

import com.stapubox.entity.Booking;
import com.stapubox.entity.Slot;
import com.stapubox.exception.BusinessException;
import com.stapubox.repository.BookingRepository;
import com.stapubox.repository.SlotRepository;
import com.stapubox.request.BookingRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final SlotRepository slotRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    public Booking bookSlot(BookingRequest req) {

        Slot slot = slotRepository.findByIdForUpdate(req.getSlotId())
                .orElseThrow(() -> new BusinessException("Slot not found"));

        if (!slot.isAvailable()) {
            throw new BusinessException("Slot already booked");
        }

        slot.markBooked();

        return bookingRepository.save(
                new Booking(slot, req.getUserName())
        );
    }

    @Transactional
    public void cancel(Long bookingId) {

        Booking booking = bookingRepository.findByIdForUpdate(bookingId)
                .orElseThrow(() -> new BusinessException("Booking not found"));

        booking.cancel();
        booking.getSlot().markAvailable();
    }
}
