package com.telus.ds.repository;

import com.telus.ds.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {
    Artist findArtistById(Long id);
}
