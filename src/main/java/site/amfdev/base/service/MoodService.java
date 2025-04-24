package site.amfdev.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import site.amfdev.base.dto.MovieDto;
import site.amfdev.base.entity.MoodEntity;
import site.amfdev.base.entity.MovieEntity;
import site.amfdev.base.entity.UserEntity;
import site.amfdev.base.repository.MoodRepository;
import site.amfdev.base.repository.MovieRepository;
import site.amfdev.base.repository.UserRepository;

@Service
public class MoodService {

	@Autowired
	MoodRepository moodRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	MovieRepository movieRepo;

	public List<MoodEntity> findByUser(String username) {
		UserEntity user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		return moodRepo.findByUser(user);
	}

	public MoodEntity createMood(String name, String username) {
		UserEntity user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		return moodRepo.save(new MoodEntity(null, name, false, user, new ArrayList<>()));
	}

	public void deleteMood(Long id) {
		moodRepo.deleteById(id);
	}

	public List<MoodEntity> findAllPublicMoods() {
		List<MoodEntity> moods = moodRepo.findAll().stream().filter(mood -> !mood.isPrivate())
				.collect(Collectors.toList());
		return moods;
	}

	@Transactional
	public MoodEntity addMovieToMood(Long moodId, MovieDto movieDto) {
		MoodEntity mood = moodRepo.findById(moodId).orElseThrow(() -> new RuntimeException("Mood not found"));

		MovieEntity movie = movieRepo.findById(movieDto.getId()).orElseGet(() -> movieRepo
				.save(new MovieEntity(movieDto.getId(), movieDto.getTitle(), movieDto.getPoster_path(), null)));

		if (!mood.getMovies().contains(movie)) {
			mood.getMovies().add(movie);
		}
		return moodRepo.save(mood);
	}

	public MoodEntity updatePrivacityMood(Long moodId, String username) {
		MoodEntity mood = moodRepo.findById(moodId).orElseThrow(() -> new RuntimeException("Mood not found"));
		if (username.equalsIgnoreCase(mood.getUser().getUsername())) {
			mood.setPrivate(!mood.isPrivate());
			return moodRepo.save(mood);
		} else {
			throw new AuthenticationCredentialsNotFoundException("Not authorized");
		}
	}

}
