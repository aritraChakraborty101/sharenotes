package com.aritra.sharenotes.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.flywaydb.database.postgresql.PostgreSQLConfigurationExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("!test")
@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway(DataSource dataSource) {
        FluentConfiguration config = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration");

        // Disable advisory locking — required for PgBouncer transaction pooler (port 6543)
        config.getPluginRegister()
                .getPlugin(PostgreSQLConfigurationExtension.class)
                .setTransactionalLock(false);

        Flyway flyway = config.load();
        flyway.migrate();
        return flyway;
    }
}

