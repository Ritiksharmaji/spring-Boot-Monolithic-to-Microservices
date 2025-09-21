package com.app_ecom.controller;

import com.app_ecom.dto.UserRequest;
import com.app_ecom.dto.UserResponse;
import com.app_ecom.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {
    //private List<User> users = new ArrayList<>();
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
//    public List<User> getUsers(){
    public List<UserResponse> getUsers(){
        /*
        ways to return the response
         */
        // return userService.fetchAllUsers();
        return new ResponseEntity<> (userService.fetchAllUsers(), HttpStatus.OK).getBody();
        //return ResponseEntity.ok(userService.fetchAllUsers()).getBody();

    }

    @PostMapping("/api/users")
    public ResponseEntity<String>  createUser( @RequestBody UserRequest userRequest){
        userService.addUser(userRequest);
        return ResponseEntity.ok("User Added");
    }

//    @GetMapping("/api/users/{id}")
//    public ResponseEntity<User> getUser(@PathVariable Long id){
//        User user = userService.fetchUser(id);
//        if(user == null){
//            return ResponseEntity.notFound().build();
//        }else{
//            return ResponseEntity.ok(user);
//        }
//
//    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
       return userService.fetchUser(id)
               .map(ResponseEntity::ok)
               .orElseGet(()-> ResponseEntity.notFound().build());

    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> UpdateUser(@PathVariable Long id, @RequestBody UserRequest updateUserRequest){
        boolean updated = userService.updateUser(id, updateUserRequest);
        if (updated) {
            return ResponseEntity.ok("user updated"); // return updated user
        } else {
            return ResponseEntity.notFound().build(); // 404 if user not found
        }

    }

}
