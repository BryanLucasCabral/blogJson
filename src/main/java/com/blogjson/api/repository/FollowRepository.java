package com.blogjson.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogjson.api.model.Follow;
import com.blogjson.api.model.FollowId;
import com.blogjson.api.model.User;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    List<Follow> findByFollowedUser(User followedUser);

    List<Follow> findByFollowingUser(User folloWingUser);
}
