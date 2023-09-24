package com.mys.projectblogsearch.db.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;

@Configuration
public class AuditorAwareConfiguration {

    @Bean(name = "utcDateTimeProvider")
    public DateTimeProvider utcDateTimeProvider() {

        return () -> Optional.of(LocalDateTime.now(ZoneId.of("UTC")));
    }

}
