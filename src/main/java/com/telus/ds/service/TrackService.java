package com.telus.ds.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.Track;
import com.telus.ds.repository.TrackRepository;

@Service
public class TrackService {
	
	@Autowired
	private TrackRepository trackRepository;
	
	public Track getTrack(String isrc) {
		return trackRepository.findByIsrc(isrc);
	}
	
	public Track create(Track track) {
		
		track.setCreationDate(LocalDateTime.now());
		return trackRepository.save(track);
	}

	public List<Track> getTracks() {
		return trackRepository.findAll();
	}
}
