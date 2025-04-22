package site.amfdev.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.amfdev.base.entity.Rol;

/**
 * @author Andres Mariano Fernández
 * 
 *         Dto que retorna el rol y username del usuario registrado.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {

	private String username;
	private Rol rol;

}
