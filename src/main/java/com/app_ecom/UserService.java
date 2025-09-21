package com.app_ecom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
   private final List<User> users = new ArrayList<>();
    private final UserRepository userRepository;
    private Long nextId = 1L;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    public List<User> fetchAllUsers(){
//        return users;
//    }
    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

//    public void addUser( User user){
//       // user.setId(nextId++);  to add the id by application not giving controller to user to create id
//        users.add(user);
//    }
    public void addUser( User user){
        userRepository.save(user);
    }

//    public User fetchUser(Long id){
//        for(User user: users){
//            if(user.getId().equals(id))
//                return user;
//        }
//        return null;
//    }
//    public Optional<User> fetchUser(Long id){
//        return users.stream()
//                .filter(user-> user.getId().equals(id))
//                .findFirst();
//    }
    public Optional<User> fetchUser(Long id){
        return userRepository.findById(id);
    }

    // for updating user
    public boolean updateUser(Long id, User updateUser){
        return userRepository.findById(id)
                .map(existingUser->{
                    existingUser.setFirstName(updateUser.getFirstName());
                    existingUser.setLastName(updateUser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }


}
