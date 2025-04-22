package site.amfdev.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Andres Mariano Fernández
 * 
 *         Respuesta enviada al logearse. Contiene token, expiracion del mimso,
 *         nombre de usuario y rol.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

	private String username;
	private String role;
	private String token;
	private long expirationTime;

}
