package com.stapubox.service;

import com.stapubox.entity.Venue;
import com.stapubox.exception.BusinessException;
import com.stapubox.repository.SportRepository;
import com.stapubox.repository.VenueRepository;
import com.stapubox.request.VenueRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;
    private final SportRepository sportRepository;

    public Venue createVenue(VenueRequest request) {

        sportRepository.findBySportCode(request.getSportCode())
                .orElseThrow(() -> new BusinessException("Invalid sportId"));

        Venue venue = new Venue();
        venue.setName(request.getName());
        venue.setCity(request.getCity());
        venue.setSportId(request.getSportCode());

        return venueRepository.save(venue);
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }
}

