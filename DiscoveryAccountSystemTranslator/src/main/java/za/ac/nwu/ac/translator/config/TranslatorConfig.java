package za.ac.nwu.ac.translator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.ac.nwu.ac.repo.config.RepositoryConfig;

@Import({RepositoryConfig.class}) //pull all parts from the RepoConfig into this file too
@Configuration
public class TranslatorConfig {
}
