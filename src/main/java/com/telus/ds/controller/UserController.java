package com.telus.ds.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telus.ds.entity.User;
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

    @GetMapping("/getTrack")
    public UserDTO getUser(@RequestParam("id") int id) {

        User userFound = userService.getUser(id);
        if (userFound == null) {
            throw new ResourceNotFoundException("User not found with id=" + id);
        }
        return convertToDTO(userFound);
    }

    @GetMapping("/getTracks")
    public List<UserDTO> getTracks() {
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
