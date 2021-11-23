package com.telus.ds.controller;

import com.telus.ds.entity.Artist;
import com.telus.ds.entity.dto.ArtistDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.ArtistService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getArtist")
    public ArtistDTO getArtist(@RequestParam("id") Long id){
        Artist artist = artistService.findById(id);
        if(artist == null ){
            throw new ResourceNotFoundException("Artist not found with id=" + id);
        }
        return convertToDTO(artist);
    }

    private ArtistDTO convertToDTO(Artist artist) {
    	configModelMapper();
        return modelMapper.map(artist, ArtistDTO.class);
    }
    
	private void configModelMapper() {
		if(!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)){
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		}
	}
}
