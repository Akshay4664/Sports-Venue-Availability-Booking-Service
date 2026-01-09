package com.stapubox.controller;

import com.stapubox.entity.Slot;
import com.stapubox.request.SlotRequest;
import com.stapubox.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/venues")
public class SlotController {

    private final SlotService slotService;

    @PostMapping("/{venueId}/slots")
    public Slot createSlot(@PathVariable Long venueId,
                           @RequestBody SlotRequest req) {
        return slotService.createSlot(venueId, req);
    }
}

