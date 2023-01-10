package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

	@Autowired
	MovieService movieService;

	@PostMapping("/add-movie")
	public ResponseEntity<String> addMovie(@RequestBody Movie movie){
		movieService.addMovie(movie);
		return new ResponseEntity<>("new movie added Successfully", HttpStatus.CREATED);
	}

	@PostMapping("/add-director")
	public ResponseEntity<String> addDirector(@RequestBody Director director){
		movieService.addDirector(director);
		return new ResponseEntity<>("new director added Successfully", HttpStatus.CREATED);
	}

	@PutMapping("/add-movie-director-pair")
	public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName){
		movieService.addMovieDirectorPair(movieName, directorName);
		return new ResponseEntity<>("Movie-Director pair added Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/get-movie-by-name/{movieName}")
	public ResponseEntity<Movie> getMovieByName(@PathVariable String movieName){
		Movie movie = null;
		movie = movieService.getMovieByName(movieName);
		return new ResponseEntity<>(movie, HttpStatus.CREATED);
	}

	@GetMapping("/get-director-by-name/{directorName}")
	public ResponseEntity<Director> getDirectorByName(@PathVariable String directorName){
		Director director = null;
		director = movieService.getDirectorByName(directorName);
		return new ResponseEntity<>(director, HttpStatus.CREATED);
	}

	@GetMapping("/get-movies-by-director-name/{directorName}")
	public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String directorName){
		List<String> movieList = null;

		movieList = movieService.getMoviesByDirectorName(directorName);
		return new ResponseEntity<>(movieList, HttpStatus.CREATED);
	}

	@GetMapping("/get-all-movies")
	public ResponseEntity<List<String>> findAllMovies(){
		List<String> movieList = null;

		movieList = movieService.findAllMovies();
		return new ResponseEntity<>(movieList, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete-director-by-name")
	public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName){
		movieService.deleteDirectorByName(directorName);
		return new ResponseEntity<>("Director and his movies are deleted", HttpStatus.CREATED);
	}

	@DeleteMapping("/delete-all-directors")
	public ResponseEntity<String> deleteAllDirectors(){
		movieService.deleteAllDirectors();
		return new ResponseEntity<>("Directors and their movies are deleted", HttpStatus.CREATED);
	}




}
