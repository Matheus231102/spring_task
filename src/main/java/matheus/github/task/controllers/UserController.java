package matheus.github.task.controllers;

import jakarta.validation.Valid;
import matheus.github.task.dto.userdto.UserDTO;
import matheus.github.task.dto.userdto.UserRDTO;
import matheus.github.task.exception.exceptions.EmailAlreadyExistsException;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.exception.exceptions.UsernameAlreadyExistsException;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PathConstants.DEFAULT_USERS_PATH)
public class UserController {

     @Autowired
     private UserServiceInterface userService;

     @GetMapping("/{userid}")
     public ResponseEntity<UserRDTO> getUserById(@PathVariable UUID userid) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserById(userid);
          return ResponseEntity.ok(userRDTO);
     }

     //TODO verificar o por que do retorno de 401 em path errados
     @GetMapping("/username/{username}")
     public ResponseEntity<UserRDTO> getUserByUsername(@PathVariable String username) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserByUsername(username);
          return ResponseEntity.ok(userRDTO);
     }

     @GetMapping("/email/{email}")
     public ResponseEntity<UserRDTO> getUserByEmail(@PathVariable String email) throws UserNotFoundException {
          UserRDTO userRDTO = userService.getUserByEmail(email);
          return ResponseEntity.ok(userRDTO);
     }

     @GetMapping("/all")
     public ResponseEntity<List<UserRDTO>> getAllUsers() {
          return ResponseEntity.ok(userService.getAllUsers());
     }

     @PostMapping
     public ResponseEntity<List<UserRDTO>> insertUsers(@RequestBody @Valid List<UserDTO> userDTOList) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
          List<UserRDTO> usersRDTO = userService.insertUsers(userDTOList);
          return ResponseEntity
                  .status(HttpStatus.CREATED)
                  .body(usersRDTO);
     }

     @DeleteMapping("/{userid}")
     public ResponseEntity removeUserById(@RequestParam UUID userid) throws UserNotFoundException {
          userService.removeUserById(userid);
          return ResponseEntity.noContent().build();
     }

     @PutMapping("/{userid}")
     private ResponseEntity<UserRDTO> updateUserById(@PathVariable UUID userid,
                                                     @RequestBody @Valid UserDTO userDTO) throws UserNotFoundException, UsernameAlreadyExistsException, EmailAlreadyExistsException {
          UserRDTO userRDTO = userService.updateUserById(userid, userDTO);
          return ResponseEntity.ok(userRDTO);
     }

}

