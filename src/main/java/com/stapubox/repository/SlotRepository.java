package com.stapubox.repository;

import com.stapubox.entity.Slot;
import com.stapubox.entity.Venue;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Slot s where s.id = :id")
    Optional<Slot> findByIdForUpdate(Long id);

    @Query("""
      select count(s) > 0 from Slot s
      where s.venue.id = :venueId
      and s.startTime < :end
      and s.endTime > :start
    """)
    boolean hasOverlap(Long venueId,
                       LocalDateTime start,
                       LocalDateTime end);

    @Query("""
    select distinct v
    from Venue v
    join Slot s on s.venue.id = v.id
    where v.sportId = :sportId
      and s.status = 'AVAILABLE'
      and s.startTime <= :startTime
      and s.endTime >= :endTime
""")
    List<Venue> findAvailableVenues(
            String sportId,
            LocalDateTime startTime,
            LocalDateTime endTime
    );

}

