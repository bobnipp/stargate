package com.afresearchlab.stargate.users;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/current")
    @PreAuthorize("#oauth2.hasScope('stargate.read')")
    public UserDTO getCurrentUser(Principal principal) {
        if (principal != null && principal.getName() != null) {
            return new UserDTO(966, principal.getName(), "", "", "", "");
        }

        return new UserDTO(967, "unknown", "", "", "", "");
    }

    @GetMapping("/{id}")
    @PreAuthorize("#oauth2.hasScope('stargate.read')")
    public UserDTO getUser(@PathVariable("id") Integer userId) {
        return repository.getUserById(userId);
    }

    @GetMapping("")
    @PreAuthorize("#oauth2.hasScope('stargate.read')")
    public List<UserDTO> getAll() {
        return repository.getUsers();
    }

    @PostMapping(value = "", consumes = "application/json")
    @PreAuthorize("#oauth2.hasScope('stargate.write')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO user) {
        return repository.save(user);
    }
}
