package com.blogjson.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blogjson.api.model.Post;
import com.blogjson.api.repository.PostRepository;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;

    public Post cadastrarPost(Post post){
        return postRepository.save(post);
    }

    public Page<Post> listarPosts(Pageable paginacao) {
        return postRepository.findAll(paginacao);
    }

    public Post buscarPostPeloId(Long id) {
        Optional<Post> postOpt = postRepository.findById(id);

        if (postOpt.isPresent()) {
            return postOpt.get();
        }
        return null;
    }

    public List<Post> buscarPostPeloTitle(String title){
        return postRepository.findByTitleContaining(title);
    }

    public Post atualizarPost(Long id, Post dadosPost){
        Optional<Post> postOpt = postRepository.findById(id);

        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            
            post.setBody(dadosPost.getBody());
            post.setStatus(dadosPost.getStatus());
            post.setTitle(dadosPost.getTitle());
            post.setUser(dadosPost.getUser());
            
            postRepository.save(post);
        }
        return null;
    }

    public Void deletarPost(Long id){ 
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isPresent()) {
            postRepository.deleteById(id);
        }

        return null;
    }
}
