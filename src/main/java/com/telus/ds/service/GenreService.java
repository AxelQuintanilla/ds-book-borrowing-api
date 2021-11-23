package com.telus.ds.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.Genre;
import com.telus.ds.repository.GenreRepository;

@Service
public class GenreService {
        @Autowired
	private GenreRepository GenreRepository;
	
	public Genre getGenre(Integer genreId) {
		return GenreRepository.findByGenreId(genreId);
	}
	
	public Genre create(Genre genre) {
		genre.setCreationDate(LocalDateTime.now());
		return GenreRepository.save(genre);
	}

	public List<Genre> getGenres() {
		return GenreRepository.findAll();
	}
}
