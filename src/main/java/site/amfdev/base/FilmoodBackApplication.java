package site.amfdev.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.transaction.Transactional;
import site.amfdev.base.dto.RegisterUserDto;
import site.amfdev.base.repository.UserRepository;
import site.amfdev.base.service.AuthenticationService;

@SpringBootApplication
public class FilmoodBackApplication {

	private static final Logger log = LoggerFactory.getLogger(FilmoodBackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FilmoodBackApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner crearAdmin(AuthenticationService authService, UserRepository userRepo) {
		return args -> {
			if (userRepo.count() == 0) {
				RegisterUserDto user = new RegisterUserDto("User", "user", "12345");
				authService.signupUser(user);
			} else {
				log.warn("===> There are users in the database");
			}
		};
	}
}