package com.telus.ds.repository;

import com.telus.ds.entity.Discography;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscographyRepository extends JpaRepository<Discography, Integer> {
  Discography findByYear(String year);
}
