package User_Service.User_Service.controller;

import User_Service.User_Service.dto.LoginRequestDto;
import User_Service.User_Service.dto.LoginResponseDto;
import User_Service.User_Service.dto.UserCreateRequestDto;
import User_Service.User_Service.dto.UserResponseDto;
import User_Service.User_Service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserCreateRequestDto createRequestDto){
          UserResponseDto created = userService.register(createRequestDto);
          return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login( @Valid @RequestBody LoginRequestDto loginRequestDto){

      LoginResponseDto loggedInUser = userService.login(loginRequestDto);

      return ResponseEntity.ok(loggedInUser);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDto> getByid(@PathVariable Long id, Authentication authentication){

      UserResponseDto dto = userService.getById(id);

      return ResponseEntity.ok(dto);

  }
}
