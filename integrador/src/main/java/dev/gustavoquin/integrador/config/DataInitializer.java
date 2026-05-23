package dev.gustavoquin.integrador.config;

import dev.gustavoquin.integrador.model.entity.News;
import dev.gustavoquin.integrador.model.entity.Testimony;
import dev.gustavoquin.integrador.model.entity.User;
import dev.gustavoquin.integrador.model.repository.NewsRepository;
import dev.gustavoquin.integrador.model.repository.TestimonyRepository;
import dev.gustavoquin.integrador.model.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   NewsRepository newsRepository,
                                   TestimonyRepository testimonyRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUsername("Gustaf");
                admin.setPassword(passwordEncoder.encode("1206"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            if (newsRepository.count() == 0) {
                News n1 = new News();
                n1.setTitle("Nueva alianza con comunidades del Guayas");
                n1.setPublishedAt(LocalDateTime.now().minusDays(1));

                News n2 = new News();
                n2.setTitle("Reporte de Impacto Q3 ya disponible");
                n2.setPublishedAt(LocalDateTime.now().minusDays(3));

                News n3 = new News();
                n3.setTitle("Proyecto de reforestación en la Amazonía");
                n3.setPublishedAt(LocalDateTime.now().minusDays(5));

                newsRepository.saveAll(Arrays.asList(n1, n2, n3));
            }

            if (testimonyRepository.count() == 0) {
                Testimony t1 = new Testimony();
                t1.setName("Elni Galindo");
                t1.setRole("Agricultor, Cayambe");
                t1.setContent("Gracias a los talleres de riego, este año hemos triplicado la producción de papas.");

                testimonyRepository.save(t1);
            }

            System.out.println("Datos de Ecuador Comparte inicializados correctamente.");
        };
    }
}