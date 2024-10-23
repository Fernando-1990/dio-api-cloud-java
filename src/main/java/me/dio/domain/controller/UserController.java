package me.dio.domain.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.dio.domain.model.User;
import me.dio.domain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Search for a user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @Operation(summary = "Create a new user")
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);

    }
    @Operation(summary = "List all registered users")
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        var user1 = userService.findAllUsers();
        return ResponseEntity.ok(user1);
    }
    @Operation(summary = "Delete an user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteByid(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Update an user")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody User updatedUser) {
        userService.update(id, updatedUser);
        return ResponseEntity.noContent().build();
    }
}
