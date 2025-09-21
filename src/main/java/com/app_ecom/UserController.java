package com.app_ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    //private List<User> users = new ArrayList<>();
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getUsers(){

        /*
        ways to return the response
         */
        // return userService.fetchAllUsers();
        return new ResponseEntity<> (userService.fetchAllUsers(), HttpStatus.OK).getBody();
        //return ResponseEntity.ok(userService.fetchAllUsers()).getBody();

    }

    @PostMapping("/api/users")
    public ResponseEntity<String>  createUser( @RequestBody User user){
//        users.add(user);
        userService.addUser(user);
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
    public ResponseEntity<User> getUser(@PathVariable Long id){
       return userService.fetchUser(id)
               .map(ResponseEntity::ok)
               .orElseGet(()-> ResponseEntity.notFound().build());

    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable Long id){

    }

}
