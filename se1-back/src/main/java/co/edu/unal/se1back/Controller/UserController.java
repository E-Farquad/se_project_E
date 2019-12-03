package co.edu.unal.se1back.Controller;

import co.edu.unal.se1back.Model.User;
import co.edu.unal.se1back.Repository.UserRepository;
import co.edu.unal.se1back.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {
/*

    @Autowired
    UserRepository userRepository;



        Log In

        If any credentials of the user is incorrect the function return false, but
        if the two credentials are correct return true


    @GetMapping("/user")
    public boolean logIn (String username, String password){
        final User user = userRepository.getUserByUsername(username);
        boolean login = false;
        if (user.getUsername() == username){
            if(user.getPassword() == password){
                login = true;
            }
        }
        else {
            login = false;
        }
        return login;
    }


        Check user rol.

        If the username correspond a Student the function return "Student",
        if the username correspond a Teacher the function return "Teacher"


    @GetMapping("/user")
    public String checkUserRol(String username){
        final User user = userRepository.getUserByUsername(username);
        String rol = "";
        if (user.getRol() == "Student"){
            rol = "Student";
        }
        else{
            rol = "Teacher";
        }
        return rol;
    }



        Exceptions


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    */

}
