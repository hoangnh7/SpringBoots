package com.example.demo.controller;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.model.request.UpdateUserReq;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    //    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserReq req) {
        UserDto result = userService.createUser(req);
        return ResponseEntity.ok(result);
    }
//    @GetMapping("/search")
//    public ResponseEntity<?> searchUser(@RequestParam(name = "keyword",required = false,defaultValue = "") String name){
//        List<UserDto> result = userService.searchByName(name);
//        return ResponseEntity.ok(result);
//    }
    @GetMapping("")
    public ResponseEntity<?> getListUser(){
//        List<User> users = userService.getListUser();
        List<UserDto> users = userService.getListUser();
        System.out.println(users);
        return ResponseEntity.ok(users);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int  id){
        UserDto result = userService.getById(id);
        return ResponseEntity.ok(result);
    }
    //    @PostMapping("")
//    public ResponseEntity<?> createUser(){
//        return null;
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateUser(@RequestBody UpdateUserReq req, @PathVariable int id){
//        UserDto result = userService.updateUser(req,id);
//        return ResponseEntity.ok(result);
//
//    }
    //    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable int id){
//        userService.deleteUser(id);
//        return ResponseEntity.ok("DELETED USER");
//
//    }
//    @GetMapping("/test-transaction")
//    public ResponseEntity<?> testTransaction() {
//        userService.testTransaction();
//
//        return ResponseEntity.status(HttpStatus.OK).body("Thành công");
//    }
//    @GetMapping("/search")
//    public ResponseEntity<?> getListUser(@RequestParam String keyword) throws InterruptedException {
//        List<UserDto> result = userService.getListUserByName(keyword);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
    @GetMapping("/search")
    public ResponseEntity<?> getListUser(@RequestParam String keyword) throws InterruptedException {
        List<UserDto> result = userService.getListUserByName(keyword);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}