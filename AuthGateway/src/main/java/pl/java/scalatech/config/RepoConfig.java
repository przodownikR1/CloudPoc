package pl.java.scalatech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages="pl.java.scalatech.repository")
public class RepoConfig {

}
