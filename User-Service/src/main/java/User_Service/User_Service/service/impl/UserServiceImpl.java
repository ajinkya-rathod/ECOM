package User_Service.User_Service.service.impl;

import User_Service.User_Service.dto.LoginRequestDto;
import User_Service.User_Service.dto.LoginResponseDto;
import User_Service.User_Service.dto.UserCreateRequestDto;
import User_Service.User_Service.dto.UserResponseDto;
import User_Service.User_Service.entity.User;
import User_Service.User_Service.repo.UserRepository;
import User_Service.User_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto register(UserCreateRequestDto createRequestDto) {

        if(userRepository.existsByEmail(createRequestDto.getEmail())){
            throw new RuntimeException("email elaredy exist");
        }

        User user = new User();
        user.setName(createRequestDto.getName());
        user.setEmail(createRequestDto.getEmail());
        user.setPassword(createRequestDto.getPassword());
       User Saveduser = userRepository.save(user);

       return new UserResponseDto(Saveduser);


        return null;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto getById(Long id) {
        return null;
    }
}
