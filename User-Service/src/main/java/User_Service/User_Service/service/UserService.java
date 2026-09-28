package User_Service.User_Service.service;

import User_Service.User_Service.dto.LoginRequestDto;
import User_Service.User_Service.dto.LoginResponseDto;
import User_Service.User_Service.dto.UserCreateRequestDto;
import User_Service.User_Service.dto.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserCreateRequestDto createRequestDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    UserResponseDto getById(Long id);
}
