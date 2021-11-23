package com.telus.ds.service;

import com.telus.ds.entity.Artist;
import com.telus.ds.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    public Artist findById(Long id){
        return artistRepository.findArtistById(id);
    }
}
