package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.exception.exceptions.EmailAlreadyExistsException;
import matheus.github.task.exception.exceptions.UsernameAlreadyExistsException;
import matheus.github.task.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

     @Autowired
     private UserServiceInterface userService;

     @GetMapping
     @ResponseStatus(HttpStatus.OK)
     public UserRDTO getUserById(@RequestParam(name = "userid") UUID id) throws UserNotFoundException {
          return userService.getUserById(id);
     }

     @GetMapping(path = "/username/{username}")
     @ResponseStatus(HttpStatus.OK)
     public UserRDTO getUserByUsername(@PathVariable(name = "username") String username) throws UserNotFoundException {
          return userService.getUserByUsername(username);
     }


     @GetMapping(path = "/email/{email}")
     @ResponseStatus(HttpStatus.OK)
     public UserRDTO getUserByEmail(@PathVariable(name = "email") String email) throws UserNotFoundException {
          return userService.getUserByEmail(email);
     }

     @GetMapping(path = "/all")
     @ResponseStatus(HttpStatus.OK)
     public List<UserRDTO> getAllUsers() {
          return userService.getAllUsers();
     }

     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserRDTO insertUser(@RequestBody @Valid UserDTO userDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
          return userService.insertUser(userDTO);
     }

     @PostMapping(path = "/group")
     @ResponseStatus(HttpStatus.CREATED)
     public List<UserRDTO> insertUsers(@RequestBody @Valid List<UserDTO> userDTOList) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
          return userService.insertUsers(userDTOList);
     }

     @DeleteMapping
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public UserRDTO removeUserById(@RequestParam(name = "userid") UUID id) throws UserNotFoundException {
          return userService.removeUserById(id);
     }

}

