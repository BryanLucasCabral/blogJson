package com.blogjson.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FollowId.class)
@Entity(name = "tb_follows")
public class Follow {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "following_user_id" , nullable = false)
    private User followingUser;

    @Id
    @ManyToOne
    @JoinColumn(name = "followed_user_id", nullable = false)
    private User followedUser;

    private LocalDateTime createdAt =LocalDateTime.now();
}
