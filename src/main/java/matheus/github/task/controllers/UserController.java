package matheus.github.task.controllers;

import matheus.github.task.dto.UserDTO;
import matheus.github.task.dto.UserRDTO;
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
     public UserRDTO getUserById(@RequestParam(name = "userid") Long id) {
          return userService.getUserById(id);
     }

     @GetMapping(path = "/{username}")
     public UserRDTO getUserByUsername(@PathVariable(name = "username") String username) {
          return userService.getUserByUsername(username);
     }

     @GetMapping(path = "/all")
     public List<UserRDTO> getAllUsers() {
          return userService.getAllUsers();
     }

     @PostMapping
     public UserRDTO insertUser(@RequestBody UserDTO userDTO) {
          return userService.insertUser(userDTO);
     }

     @PostMapping(path = "/group")
     public List<UserRDTO> insertUsers(@RequestBody List<UserDTO> userDTOList) {
          return userService.insertUsers(userDTOList);
     }

     @DeleteMapping
     public UserRDTO removeUserById(@RequestParam(name = "userid") Long id) {
          return userService.removeUserById(id);
     }


}

