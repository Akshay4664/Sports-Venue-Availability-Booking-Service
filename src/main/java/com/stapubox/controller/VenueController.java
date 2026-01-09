package com.stapubox.controller;

import com.stapubox.entity.Venue;
import com.stapubox.request.VenueRequest;
import com.stapubox.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public Venue createVenue(@RequestBody VenueRequest request) {
        return venueService.createVenue(request);
    }

    @GetMapping
    public List<Venue> getVenues() {
        return venueService.getAllVenues();
    }
}

