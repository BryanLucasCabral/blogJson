package com.blogjson.api.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowId implements Serializable {
    private Long followingUser;
    private Long followedUser;

    @Override
    public boolean equals(Object o) { 
        if (this == o) return true;
        if (!(o instanceof FollowId)) return false;

        FollowId that = (FollowId) o;
        return  Objects.equals(followingUser, that.followingUser) && 
                Objects.equals(followedUser, that.getFollowedUser());
    }

    @Override
    public int hashCode(){
        return Objects.hash(followingUser, followedUser);
    }
}
