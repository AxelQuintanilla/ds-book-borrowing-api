package com.telus.ds.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telus.ds.entity.Book;
import com.telus.ds.entity.User;
import com.telus.ds.entity.dto.BookDTO;
import com.telus.ds.entity.dto.UserDTO;
import com.telus.ds.exception.ResourceNotFoundException;
import com.telus.ds.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getUser")
    public UserDTO getUser(@RequestParam("userid") Integer userid) {

        User userFound = userService.getUser(userid);
        if (userFound == null) {
            throw new ResourceNotFoundException("User not found with id=" + userid);
        }
        return convertToDTO(userFound);
    }

    @GetMapping("/getUsers")
    public List<UserDTO> getUsers() {
        return userService.getUsers()
                .stream()
                .map(t -> convertToDTO(t))
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public UserDTO create(@RequestBody User user) {
        log.info("Creating a user");
        return convertToDTO(userService.create(user));
    }
    
    @PutMapping("/update/{userid}")
    private UserDTO update(@RequestBody User userUpdated, @PathVariable("userid") int userid) {
        User user = userService.findByuserid(userid);
        log.info("Updating a user");
        return convertToDTO(userService.update(user, userUpdated));
    }
    
    @DeleteMapping("/delete/{userid}")
    private void deleteUser(@PathVariable("userid") int userid) {
    	userService.delete(userid);
    }
    
    

    private UserDTO convertToDTO(User user) {
        configModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    private void configModelMapper() {
        if (!modelMapper.getConfiguration().getMatchingStrategy().equals(MatchingStrategies.LOOSE)) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        }
    }
}
