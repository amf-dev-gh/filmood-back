package site.amfdev.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.amfdev.base.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long>{

}
