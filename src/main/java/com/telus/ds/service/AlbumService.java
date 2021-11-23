package com.telus.ds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.Album;
import com.telus.ds.repository.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	public Album getAlbum(String auid) {
		return albumRepository.findByAuid(auid);
	}
	
	public Album create(Album album) {
		return albumRepository.save(album);
	}
	
	public List<Album> getAlbums() {
		return albumRepository.findAll();
	}
}
