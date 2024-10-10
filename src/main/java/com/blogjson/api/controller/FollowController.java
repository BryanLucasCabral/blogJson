package com.blogjson.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogjson.api.model.Follow;
import com.blogjson.api.model.User;
import com.blogjson.api.service.FollowService;

@RestController
@RequestMapping("/follows")
public class FollowController {
    
    @Autowired
    private FollowService followService;

    @PostMapping
    public ResponseEntity<Follow> followUser(@RequestBody Follow follow){ 
        followService.followUser(follow.getFollowingUser(), follow.getFollowedUser());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<User>> buscarFollowers(@PathVariable Long userId){ 
        User user = new User();

        user.setId(userId);
        List<User> followers = followService.buscarFollowers(user);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<User>> buscarFollowing(@PathVariable Long userId){ 
        User user = new User();

        user.setId(userId);
        List<User> following = followService.buscarFollowing(user);
        return ResponseEntity.ok(following);
    }
}
