package site.amfdev.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.amfdev.base.entity.MovieEntity;
import site.amfdev.base.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepo;
	
	public MovieEntity save(MovieEntity movie) {
		return movieRepo.save(movie);
	}
}
