package com.stapubox.service;

import com.stapubox.entity.Slot;
import com.stapubox.entity.Venue;
import com.stapubox.exception.BusinessException;
import com.stapubox.repository.SlotRepository;
import com.stapubox.repository.VenueRepository;
import com.stapubox.request.SlotRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final SlotRepository slotRepository;
    private final VenueRepository venueRepository;

    public Slot createSlot(Long venueId, SlotRequest req) {

        if (slotRepository.hasOverlap(
                venueId, req.getStartTime(), req.getEndTime())) {
            throw new BusinessException("Slot overlap detected");
        }

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException("Venue not found"));

        Slot slot = new Slot();
        slot.setVenue(venue);
        slot.setStartTime(req.getStartTime());
        slot.setEndTime(req.getEndTime());

        return slotRepository.save(slot);
    }
}
