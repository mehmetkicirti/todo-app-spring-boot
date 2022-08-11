package com.mehmetkicirti.todoapp.utility;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class DateTimeUtils {
    private final Clock clock;
    private static DateTimeUtils instance;
    private DateTimeUtils(Clock clock){
        this.clock = clock;
    }

    public static DateTimeUtils getDateTimeConfig(Clock clock){
        if(instance == null) {
            instance = new DateTimeUtils(clock);
        }
        return instance;
    }

    public LocalDateTime getLocalDateTimeNow(){
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }
}
