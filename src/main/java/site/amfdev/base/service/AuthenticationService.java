package site.amfdev.base.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import site.amfdev.base.dto.LoginCredentialsDto;
import site.amfdev.base.dto.RegisterUserDto;
import site.amfdev.base.entity.Rol;
import site.amfdev.base.entity.UserEntity;
import site.amfdev.base.repository.UserRepository;

/**
 * 
 * @author Andres Mariano Fernández
 *
 *         Servicio de autenticaciñon y creación de nuevos usuarios
 */
@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	public UserEntity signupUser(RegisterUserDto input) {
		if (userRepository.existsByUsername(input.getUsername().toLowerCase())) {
			throw new IllegalArgumentException("The username already exists");
		}
		UserEntity newUser = new UserEntity(null, input.getFullName(), input.getUsername().toLowerCase(),
				passwordEncoder.encode(input.getPassword()), Rol.USER, new ArrayList<>());
		return userRepository.save(newUser);
	}

	public UserEntity authenticate(LoginCredentialsDto input) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(input.getUsername().toLowerCase(), input.getPassword()));
		return userRepository.findByUsername(input.getUsername()).orElseThrow();
	}
}