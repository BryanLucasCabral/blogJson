package com.blogjson.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogjson.api.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    List<Post> findByTitleContaining(String title);
}
