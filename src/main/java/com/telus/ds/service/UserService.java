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
	return userRepository.save(user);
    }
    
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    public void delete(int userid) {
    	userRepository.deleteById(userid);
    }
    
    public User findByuserid(Integer userid) {
        return userRepository.findByuserid(userid);
    }
    
    public User update(User user, User userUpdated) {
        if(userUpdated.getFirstName()!=null){
            user.setFirstName(userUpdated.getFirstName());
        }
        if(userUpdated.getLastName()!=null){
            user.setLastName(userUpdated.getLastName());
        }
        return userRepository.save(user);
    }
    
    /*public void update(User user, int userid) {
    	userRepository.save(user);
    }*/
}
