package User_Service.User_Service.mapper;

import User_Service.User_Service.dto.UserCreateRequestDto;
import User_Service.User_Service.dto.UserResponseDto;
import User_Service.User_Service.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class userMapper {

    private PasswordEncoder passwordEncoder;

    public userMapper(PasswordEncoder passwordEncoder){
      this.passwordEncoder = passwordEncoder;
    }


    public User toEntity(UserCreateRequestDto dto){

       return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phone(dto.getPhone())
                .roles("ROLE_USER").build();


    }

    public UserResponseDto toDto(User user){
        return  UserResponseDto.builder()
                  .id(user.getId())
                  .name(user.getName())
                  .email(user.getEmail())
                  .password(user.getPassword())
                  .phone(user.getPhone())
                  .roles(user.getRoles())
                  .createdAt(user.getCreatedAt())
                  .updatedAt(user.getUpdatedAt())
                  .build();
    }

}
