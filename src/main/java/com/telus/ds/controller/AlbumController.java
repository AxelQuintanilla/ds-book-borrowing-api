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

import com.telus.ds.entity.Album;
import com.telus.ds.entity.Track;
import com.telus.ds.entity.dto.AlbumDTO;
import com.telus.ds.entity.dto.TrackDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.AlbumService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/album")
public class AlbumController {

	@Autowired
	AlbumService albumService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/getAlbum")
	public AlbumDTO getAlbum(@RequestParam("auid") String auid) {
		Album albumFound = albumService.getAlbum(auid);
		
		if(albumFound == null) {
			throw new ResourceNotFoundException("Album not found in DS repository with AUID=" + auid);
		}
		
		return convetToDTO(albumFound);
	}
	
	@GetMapping("/getAlbums")
	public List<Album> getAlbums() {
		return albumService.getAlbums();
	}
	
	@PostMapping("/")
	public Album create(@RequestBody Album album) {
		return albumService.create(album);
	}
	
	private AlbumDTO convetToDTO(Album album) {
		return modelMapper.map(album,  AlbumDTO.class);
	}
	
	@SuppressWarnings("unused")
	private Album convertToEntity(AlbumDTO albumDTO) {
		return modelMapper.map(albumDTO, Album.class);
	}

}
