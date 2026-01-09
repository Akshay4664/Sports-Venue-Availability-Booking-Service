CREATE TABLE IF NOT EXISTS sport (
                                     sport_id BIGINT PRIMARY KEY,
                                     sport_code VARCHAR(50) NOT NULL,
    sport_name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS venue (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    sport_id BIGINT NOT NULL,
    FOREIGN KEY (sport_id) REFERENCES sport(sport_id)
    );

CREATE TABLE IF NOT EXISTS slot (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    venue_id BIGINT NOT NULL,
                                    start_time DATETIME NOT NULL,
                                    end_time DATETIME NOT NULL,
                                    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (venue_id) REFERENCES venue(id)
    );

CREATE TABLE IF NOT EXISTS booking (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       slot_id BIGINT NOT NULL UNIQUE,
                                       user_name VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (slot_id) REFERENCES slot(id)
    );
