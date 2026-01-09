package com.stapubox.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    private Long slotId;
    private String userName;
}