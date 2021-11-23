package com.telus.ds.controller;

import com.telus.ds.entity.Discography;
import com.telus.ds.entity.dto.DiscographyDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.DiscographyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/discography")
public class DiscographyController {

  @Autowired
  DiscographyService discographyService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping("/getDiscography")
  public DiscographyDTO getDiscography(@RequestParam("year") String year){
    Discography discographyFound =
      discographyService.getDiscography(year);

    if(discographyFound == null){
      throw new ResourceNotFoundException("Discography not found in" +
        " DS repository with year=" + year);
    }

    return convertToDTO(discographyFound);
  }

  private DiscographyDTO convertToDTO(Discography discography) {
    return modelMapper.map(discography, DiscographyDTO.class);
  }
}
