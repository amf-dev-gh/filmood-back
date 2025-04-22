package site.amfdev.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.amfdev.base.entity.MoodEntity;
import site.amfdev.base.entity.UserEntity;
import site.amfdev.base.repository.MoodRepository;
import site.amfdev.base.repository.UserRepository;

@Service
public class MoodService {

	@Autowired
	MoodRepository moodRepo;

	@Autowired
	UserRepository userRepo;

	public MoodEntity save(MoodEntity mood) {
		return moodRepo.save(mood);
	}

	public List<MoodEntity> findByUser(String username) {
		UserEntity user = userRepo.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return moodRepo.findByUser(user);
	}

	public MoodEntity createMood(String name, String username) {
		UserEntity user = userRepo.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return moodRepo.save(new MoodEntity(null, name, false, user, new ArrayList<>()));
	}

	public void deleteMood(Long id) {
		moodRepo.deleteById(id);
	}
}