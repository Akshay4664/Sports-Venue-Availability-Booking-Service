package com.stapubox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "slot",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"venue_id","start_time","end_time"}))
@Getter
@Setter
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Venue venue;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private SlotStatus status = SlotStatus.AVAILABLE;

    public boolean isAvailable() {
        return status == SlotStatus.AVAILABLE;
    }

    public void markBooked() {
        this.status = SlotStatus.BOOKED;
    }

    public void markAvailable() {
        this.status = SlotStatus.AVAILABLE;
    }
}

enum SlotStatus {
    AVAILABLE, BOOKED
}

