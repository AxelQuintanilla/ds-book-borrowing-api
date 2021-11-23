package com.telus.ds.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telus.ds.entity.Genre;
import com.telus.ds.entity.dto.GenreDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.GenreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/genre")
public class GenreController {

	@Autowired
	GenreService genreService;

	@Autowired
	private ModelMapper modelMapper;

	private GenreDTO convertToDTO(Genre genre) {
		return modelMapper.map(genre, GenreDTO.class);
	}

	@SuppressWarnings("unused")
	private Genre convertToEntity(GenreDTO genreDTO) {
		return modelMapper.map(genreDTO, Genre.class);
	}

	@GetMapping("/getGenre")
	public GenreDTO getGenre(@RequestParam("genreId") Integer genreId) {

		Genre genreFound = genreService.getGenre(genreId);

		if (genreFound == null) {
			throw new ResourceNotFoundException("Genre not found in DS repository with Genre_id=" + genreId);
		}

		return convertToDTO(genreFound);
	}

	@GetMapping("/getGenres")
	public List<Genre> getGenres() {
		return genreService.getGenres();
	}

	@PostMapping("/")
	public Genre create(@RequestBody Genre genre) {
		log.info("Creating a genre");
		return genreService.create(genre);
	}
}
