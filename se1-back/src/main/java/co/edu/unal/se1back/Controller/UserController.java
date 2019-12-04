package co.edu.unal.se1back.Controller;

import co.edu.unal.se1back.Repository.StudentRepository;
import co.edu.unal.se1back.Repository.TutorRepository;
import co.edu.unal.se1back.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TutorRepository tutorRepository;

    /*

        The function verify the login and if the user is student or tutor:

        - If both credentials of the user are correct loginValidation will be equals true
          otherwise loginValidation will be false

        - If the user are Student userRol will be equals 'Student' otherwise will be equals 'Tutor'

          The function return a List with both variables, in the position 0 is the
          loginValidation and in the position 1 is the userRol

     */

    @GetMapping("/user/login/{username}-{password}")
    public List validate_LogIn(@PathVariable(value = "username") String username,
        @PathVariable(value = "password") String password){
        List validate = new ArrayList();

        final String studentCredentials = "" + studentRepository.getStudentCredentials(username, password);
        boolean loginValidation = false;
        String userRol = "";

        if(studentCredentials != null && studentCredentials.equals("Student")){
           userRol = studentCredentials;
           loginValidation = true;
        }
        else{
            final String tutorCredentials = "" + tutorRepository.getTutorCredentials(username, password);
            if(tutorCredentials != null && tutorCredentials.equals("Tutor")){
                userRol = tutorCredentials;
                loginValidation = true;
            }
        }

        validate.add(loginValidation);
        validate.add(userRol);

        return validate;
    }
}
