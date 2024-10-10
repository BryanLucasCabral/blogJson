package com.blogjson.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogjson.api.model.Post;
import com.blogjson.api.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> cadastrarPost(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.cadastrarPost(post));
    }

    @GetMapping
    public ResponseEntity<Page<Post>> listarPosts(
            @PageableDefault(size = 10, sort = "title", direction = Direction.DESC) Pageable paginacao) {
        return ResponseEntity.ok().body(postService.listarPosts(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> buscarPostPeloId(@PathVariable Long id) {
        return ResponseEntity.ok().body(postService.buscarPostPeloId(id));
    }

    @GetMapping("/busca/{title}")
    public ResponseEntity<List<Post>> buscarPostsPeloTitle(@PathVariable String title) {
        return ResponseEntity.ok().body(postService.buscarPostPeloTitle(title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizarPost(@PathVariable Long id, Post dadosPost) {
        return ResponseEntity.ok().body(postService.atualizarPost(id, dadosPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable Long id) {
        Post post = postService.buscarPostPeloId(id);
        if (Objects.isNull(post)) {
            return ResponseEntity.notFound().build();
        }
        postService.deletarPost(id);
        return ResponseEntity.noContent().build();

    }
}
