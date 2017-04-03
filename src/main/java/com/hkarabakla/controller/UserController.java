package com.hkarabakla.controller;

import com.hkarabakla.model.User;
import com.hkarabakla.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(Optional<Integer> page, Optional<Integer> size) {

        if(!page.isPresent()) {
            page = Optional.of(0);
        }
        if(!size.isPresent()) {
            size = Optional.of(10);
        }

        Page<User> userList = userRepository.findAll(new PageRequest(page.get(), size.get()));

        return new ResponseEntity<List<User>>(userList.getContent(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable String id) {

        User user = userRepository.findOne(id);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {

        if(user == null || !isUserValid(user)) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

        user = userRepository.save(user);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    private boolean isUserValid(User user) {
        return false;
    }
}
