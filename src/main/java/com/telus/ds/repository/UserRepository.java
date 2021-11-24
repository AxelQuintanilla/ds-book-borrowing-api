package com.telus.ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telus.ds.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByuserid(Integer userid);
}
