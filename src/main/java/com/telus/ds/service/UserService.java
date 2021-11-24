package com.telus.ds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.ds.entity.User;
import com.telus.ds.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User getUser(Integer userid) {
        return userRepository.findByuserid(userid);
    } 
    
    public User create(User user) {
	/*user.setFirstName(firstName);
        user.setLastName(lastName);*/
	return userRepository.save(user);
    }
    
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
