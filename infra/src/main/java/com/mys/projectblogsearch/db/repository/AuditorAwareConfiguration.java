package com.mys.projectblogsearch.db.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "utcDateTimeProvider")
public class AuditorAwareConfiguration {

    @Bean(name = "utcDateTimeProvider")
    public DateTimeProvider utcDateTimeProvider() {

        return () -> Optional.of(LocalDateTime.now(ZoneId.of("UTC")));
    }

}
