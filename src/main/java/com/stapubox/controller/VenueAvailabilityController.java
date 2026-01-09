package com.stapubox.controller;

import com.stapubox.entity.Venue;
import com.stapubox.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/venues")
public class VenueAvailabilityController {

    private final AvailabilityService availabilityService;

    @GetMapping("/available")
    public List<Venue> getAvailableVenues(
            @RequestParam String sportCode,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startTime,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endTime) {

        return availabilityService.getAvailableVenues(
                sportCode, startTime, endTime);
    }
}
