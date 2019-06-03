package com.zmiter.moviestracker.controller;

import com.zmiter.moviestracker.entities.User;
import com.zmiter.moviestracker.dao.UsersDao;
import com.zmiter.moviestracker.exception.common.ResourceNotFoundException;
import com.zmiter.moviestracker.security.CurrentUser;
import com.zmiter.moviestracker.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UsersDao userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {

        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}