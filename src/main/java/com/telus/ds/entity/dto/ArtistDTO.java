package com.telus.ds.entity.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArtistDTO {
	
    private String name;
    private String phone;
    private Integer age;
    private List<TrackDTO> tracks;
    
}
