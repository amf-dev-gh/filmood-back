package site.amfdev.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.amfdev.base.dto.MovieDto;
import site.amfdev.base.entity.MoodEntity;
import site.amfdev.base.service.MoodService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/filmood/moods")
public class MoodController {

	@Autowired
	MoodService moodService;

	@GetMapping("/user")
	public ResponseEntity<?> findMoodsByUser(@AuthenticationPrincipal UserDetails userDetails) {
		List<MoodEntity> moods = new ArrayList<>();
		try {
			moods = moodService.findByUser(userDetails.getUsername());
			return ResponseEntity.ok(moods);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("/new/{name}")
	public ResponseEntity<?> createNewMod(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String name) {
		try {
			moodService.createMood(name, userDetails.getUsername());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMood(@PathVariable Long id) {
		try {
			moodService.deleteMood(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping("/addTo/{moodId}")
	public ResponseEntity<?> addMovieToMood(@PathVariable Long moodId, @RequestBody MovieDto movieDto) {
		try {
			moodService.addMovieToMood(moodId, movieDto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	

}