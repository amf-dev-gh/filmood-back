package site.amfdev.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Andres Mariano Fernández
 * 
 *         Dto que envía datos para registrar un nuevo usuario.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

	private String fullName;
	private String username;
	private String password;

}
