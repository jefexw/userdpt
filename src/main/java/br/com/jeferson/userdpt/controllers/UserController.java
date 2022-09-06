package br.com.jeferson.userdpt.controllers;

import br.com.jeferson.userdpt.entities.User;
import br.com.jeferson.userdpt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository rep;

    @GetMapping
    public ResponseEntity<List<User>>findAll() {
        List<User> list = rep.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id) {
        User result = rep.findById(id).get();
        return result;
    }

    @PostMapping
    public User insertUser(@RequestBody User user) {
        User result = rep.save(user);
        return result;
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity updtate(@PathVariable("id") long id,

                                  @RequestBody
                                          User user) {
        return rep.findById(id).map(record -> {
            record.setName(user.getName());
            record.setEmail(user.getEmail());
            User updated = rep.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> findById(@PathVariable long id) {
        return rep.findById(id).map(record -> {
            rep.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
