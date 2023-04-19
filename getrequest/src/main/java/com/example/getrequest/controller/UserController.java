package com.example.getrequest.controller;

import com.example.getrequest.entity.User;
import com.example.getrequest.model.dto.UserDto;
import com.example.getrequest.model.request.CreateUserReq;
import com.example.getrequest.model.request.UpdateUserReq;
import com.example.getrequest.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    //    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserReq req) {
          UserDto result = userService.createUser(req);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam(name = "keyword",required = false,defaultValue = "") String name){
        List<UserDto> result = userService.searchByName(name);
        return ResponseEntity.ok(result);
    }
    @GetMapping("")
    public ResponseEntity<?> getListUser(){
//        List<User> users = userService.getListUser();
        List<UserDto> users = userService.getListUser();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int  id){
        List<UserDto> result = userService.getById(id);
        return ResponseEntity.ok(result);
    }
//    @PostMapping("")
//    public ResponseEntity<?> createUser(){
//        return null;
//    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserReq req, @PathVariable int id){
        UserDto result = userService.updateUser(req,id);
        return ResponseEntity.ok(result);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.ok("DELETED USER");

    }
}
