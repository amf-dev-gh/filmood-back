package site.amfdev.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.amfdev.base.dto.LoginCredentialsDto;
import site.amfdev.base.dto.LoginResponseDto;
import site.amfdev.base.dto.RegisterResponseDto;
import site.amfdev.base.dto.RegisterUserDto;
import site.amfdev.base.entity.UserEntity;
import site.amfdev.base.service.AuthenticationService;
import site.amfdev.base.service.JwtService;

/**
 * @author Andres Mariano Fernández
 * 
 *         Controlador encargado de la creación y autenticación de usuarios
 */
@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * 
	 * @param credentials
	 * @return Retorna los un LoginResponse con el token, expiración del usuario logueado
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginCredentialsDto credentials) {
		UserEntity authUser = authenticationService.authenticate(credentials);
		String JWToken = jwtService.generateToken(authUser);
		LoginResponseDto response = new LoginResponseDto(authUser.getUsername(), authUser.getRol().toString(), JWToken,
				jwtService.getExpirationTime());
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param registerUserDto
	 * @return Retorna un response con los datos del nuevo usuario registrado en la BBDD.
	 */
	@PostMapping("/singup")
	public ResponseEntity<?> singUp(@RequestBody RegisterUserDto registerUserDto) {
		try {
			UserEntity newUser = authenticationService.signupUser(registerUserDto);
			RegisterResponseDto response = new RegisterResponseDto(newUser.getUsername(), newUser.getRol());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}