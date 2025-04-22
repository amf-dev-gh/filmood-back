package site.amfdev.base.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import site.amfdev.base.entity.UserEntity;

/**
 * 
 * @author Andres Mariano Fernández
 *
 *         Repositorio de persistencia JPA en BBDD.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	/*
	 * Agregar mas metodos personalizados de JPA...
	 */

	Optional<UserEntity> findByUsername(String username);

	boolean existsByUsername(String username);

}