package com.blogjson.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogjson.api.model.Follow;
import com.blogjson.api.model.User;
import com.blogjson.api.repository.FollowRepository;

@Service
public class FollowService {
    
    @Autowired
    private FollowRepository followRepository;

    public void followUser(User followingUser, User followedUser) {
        Follow follow = new Follow();

        follow.setFollowingUser(followingUser);
        follow.setFollowedUser(followedUser);;
        followRepository.save(follow);
    }

    public List<User> buscarFollowers(User user) {
        return followRepository.findByFollowedUser(user)
        .stream()
        .map(Follow::getFollowedUser)
        .collect(Collectors.toList());
    }

    public List<User> buscarFollowing(User user){ 
        return followRepository.findByFollowingUser(user)
        .stream()
        .map(Follow::getFollowingUser)
        .collect(Collectors.toList());
    }
}
