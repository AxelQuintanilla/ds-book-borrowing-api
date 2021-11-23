package com.telus.ds.service;

import com.telus.ds.entity.Discography;
import com.telus.ds.repository.DiscographyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscographyService {

  @Autowired
  private DiscographyRepository discographyRepository;

  public Discography getDiscography(String year){
    return discographyRepository.findByYear(year);
  }
}
