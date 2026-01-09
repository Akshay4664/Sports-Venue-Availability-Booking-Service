package com.stapubox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "venue",
        indexes = @Index(columnList = "city,sport_id"))
@Getter
@Setter
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;

    @Column(name = "sport_id", nullable = false)
    private String sportId;
}
