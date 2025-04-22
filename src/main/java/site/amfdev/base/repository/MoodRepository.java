package site.amfdev.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import site.amfdev.base.entity.MoodEntity;
import site.amfdev.base.entity.UserEntity;

public interface MoodRepository extends JpaRepository<MoodEntity,Long> {
	
	public List<MoodEntity> findByUser(UserEntity user);

}
