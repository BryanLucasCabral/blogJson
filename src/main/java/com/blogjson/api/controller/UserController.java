package com.blogjson.api.controller;

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

import com.blogjson.api.model.User;
import com.blogjson.api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> cadastrarUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.cadastrarUser(user));
    }

    @GetMapping
    public ResponseEntity<Page<User>> listarUsers(
            @PageableDefault(size = 10, page = 1, sort = "username", direction = Direction.DESC) Pageable paginacao) {
        return ResponseEntity.ok().body(userService.listarUsers(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarUserPeloId(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.buscarUserPeloId(id));
    }

    @GetMapping("/busca/{username}")
    public ResponseEntity<User> buscarUserPeloNome(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.buscarUserPeloUsername(username));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarUser(@PathVariable Long id, User dadosUser) {
        return ResponseEntity.ok().body(userService.atualizarDadosUser(id, dadosUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUser(@PathVariable Long id) {
        User user = userService.buscarUserPeloId(id);
        if (Objects.isNull(user)) {
            return ResponseEntity.notFound().build();
        }
        userService.deletarUser(id);
        return ResponseEntity.noContent().build();

    }
}
