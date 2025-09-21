package com.app_ecom;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    public List<User> fetchAllUsers(){
        return users;
    }

    public void addUser( User user){
       // user.setId(nextId++);  to add the id by application not giving controller to user to create id
        users.add(user);
    }

//    public User fetchUser(Long id){
//        for(User user: users){
//            if(user.getId().equals(id))
//                return user;
//        }
//        return null;
//    }
    public Optional<User> fetchUser(Long id){
        return users.stream()
                .filter(user-> user.getId().equals(id))
                .findFirst();
    }


}
