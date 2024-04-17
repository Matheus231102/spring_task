package matheus.github.task.controllers;

import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;
import matheus.github.task.exception.exceptions.InvalidUserException;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

     @Autowired
     private UserServiceInterface userService;

     @GetMapping
     public UserRDTO getUserById(@RequestParam(name = "userid") Long id) throws UserNotFoundException {
          return userService.getUserById(id);
     }

     @GetMapping(path = "/{username}")
     public UserRDTO getUserByUsername(@PathVariable(name = "username") String username) throws UserNotFoundException {
          return userService.getUserByUsername(username);
     }

     @GetMapping(path = "/all")
     public List<UserRDTO> getAllUsers() {
          return userService.getAllUsers();
     }

     @PostMapping
     public UserRDTO insertUser(@RequestBody UserDTO userDTO) throws InvalidUserException {
          return userService.insertUser(userDTO);
     }

     @PostMapping(path = "/group")
     public List<UserRDTO> insertUsers(@RequestBody List<UserDTO> userDTOList) throws InvalidUserException {
          return userService.insertUsers(userDTOList);
     }

     @DeleteMapping
     public UserRDTO removeUserById(@RequestParam(name = "userid") Long id) throws UserNotFoundException {
          return userService.removeUserById(id);
     }


}

