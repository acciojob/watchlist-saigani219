package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

	HashMap<String, Movie> movieDb = new HashMap<>();

	HashMap<String, Director> directorDb = new HashMap<>();

	HashMap<String, List<String>> movieDirectorDb = new HashMap<>();


	public void addMovieToDb(Movie movie) {
		String movieName = movie.getName();

		movieDb.put(movieName, movie);
	}

	public void addDirectorToDb(Director director) {
		String directorName = director.getName();

		directorDb.put(directorName, director);
	}

	public void addMovieDirectorPairToDb(String movieName, String directorName) {
		if(movieDirectorDb.containsKey(directorName)){
			movieDirectorDb.get(directorName).add(movieName);
			return;
		}
		List<String> moviesByDirector = new ArrayList<>();
		moviesByDirector.add(movieName);
		movieDirectorDb.put(directorName, moviesByDirector);
	}

	public Movie getMovieFromMovieDb(String movieName) {
		if(movieDb.containsKey(movieName))
			return movieDb.get(movieName);
		return null;
	}


	public List<String> getMoviesFromDb(String directorName) {
		if(movieDirectorDb.containsKey(directorName)){
			return movieDirectorDb.get(directorName);
		}
		return null;
	}

	public List<String> findAllMoviesFromDb() {
		List<String> movieList = new ArrayList<>();
		for(String movie : movieDb.keySet()){
			movieList.add(movie);
		}
		return movieList;
	}

	public void deleteDirectorAndMoviesFromDb(String directorName) {
		for(String movies : movieDirectorDb.get(directorName)){
			if(movieDb.containsKey(movies)){
				movieDb.remove(movies);
			}
		}
			directorDb.remove(directorName);
			movieDirectorDb.remove(directorName);
	}

	public void deleteAllDirectorsAndTheirMoviesFromDb() {
		for(List<String> movieListsOfDirectors : movieDirectorDb.values()){
			for(String movies : movieListsOfDirectors){
				if(movieDb.containsKey(movies)){
					movieDb.remove(movies);
				}
			}
		}
		directorDb.clear();
		movieDirectorDb.clear();
	}

	public Director getDirectorFromDb(String directorName) {
		if(directorDb.containsKey(directorName))
			return directorDb.get(directorName);
		return null;
	}
}
