package com.stapubox.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SlotRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}