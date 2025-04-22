package site.amfdev.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Andres Mariano Fernández
 * 
 *         Credenciales recibidas para inicio de sesion
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentialsDto {

	private String username;
	private String password;

}
