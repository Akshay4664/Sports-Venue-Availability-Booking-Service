package com.stapubox.config;

import com.stapubox.entity.Sport;
import com.stapubox.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final SportRepository sportRepository;
    private final RestTemplate restTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void loadSports() {

        String url = "https://stapubox.com/sportslist/";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> sports =
                (List<Map<String, Object>>) response.get("data");

        if (sports == null || sports.isEmpty()) {
            return;
        }

        for (Map<String, Object> s : sports) {

            Long sportId = Long.valueOf(s.get("sport_id").toString());
            String sportCode = s.get("sport_code").toString();
            String sportName = s.get("sport_name").toString();

            sportRepository.findById(sportId)
                    .orElseGet(() ->
                            sportRepository.save(
                                    new Sport(sportId, sportCode, sportName)
                            )
                    );
        }
    }
}
