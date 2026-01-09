package com.stapubox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking",
        uniqueConstraints = @UniqueConstraint(columnNames = "slot_id"))
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Slot slot;

    private String userName;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.BOOKED;

    public Booking(Slot slot, String userName) {
        this.slot = slot;
        this.userName = userName;
    }

    public Booking() {}

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }
}

enum BookingStatus {
    BOOKED, CANCELLED
}

