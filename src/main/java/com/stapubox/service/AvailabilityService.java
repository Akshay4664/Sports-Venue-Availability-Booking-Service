package com.stapubox.service;

import com.stapubox.entity.Venue;
import com.stapubox.repository.SlotRepository;
import com.stapubox.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final SlotRepository slotRepository;
    private final SportRepository sportRepository;

    public List<Venue> getAvailableVenues(
            String sportCode,
            LocalDateTime startTime,
            LocalDateTime endTime) {

        // Validate sport from external API data
        sportRepository.findBySportCode(sportCode)
                .orElseThrow(() -> new RuntimeException("Invalid sportId"));

        return slotRepository.findAvailableVenues(
                sportCode, startTime, endTime);
    }
}
