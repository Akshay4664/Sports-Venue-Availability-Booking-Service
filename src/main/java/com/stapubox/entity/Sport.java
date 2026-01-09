package com.stapubox.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sport {

    @Id
    @Column(name = "sport_id", nullable = false)
    private Long id;

    @Column(name = "sport_code",  nullable = false, length = 50)
    private String sportCode;

    @Column(name = "sport_name", nullable = false, length = 100)
    private String sportName;

    public Sport(String sportCode, String sportName) {
        this.sportCode = sportCode;
        this.sportName = sportName;
    }
}
