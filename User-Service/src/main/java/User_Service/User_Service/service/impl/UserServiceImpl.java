package User_Service.User_Service.service.impl;

import User_Service.User_Service.dto.LoginRequestDto;
import User_Service.User_Service.dto.LoginResponseDto;
import User_Service.User_Service.dto.UserCreateRequestDto;
import User_Service.User_Service.dto.UserResponseDto;
import User_Service.User_Service.entity.User;
import User_Service.User_Service.exception.ResourceAlreadyExistException;
import User_Service.User_Service.exception.ResourceNotFoundException;
import User_Service.User_Service.mapper.AuthMapper;
import User_Service.User_Service.mapper.userMapper;
import User_Service.User_Service.repo.UserRepository;
import User_Service.User_Service.service.UserService;
import User_Service.User_Service.util.jwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private userMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private final jwtUtil jwtUtil;

    @Autowired
    private AuthMapper authMapper;



    @Override
    public UserResponseDto register(UserCreateRequestDto createRequestDto) {

       if(userRepository.existsByEmail(createRequestDto.getEmail())){
           throw new ResourceAlreadyExistException("user already exist for this email");
       }

       User user = userMapper.toEntity(createRequestDto);
       User savedUser = userRepository.save(user);

       return userMapper.toDto(savedUser);

    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        User user =
                userRepository.findByEmail(loginRequestDto.getEmail())
                        .orElseThrow(() -> new ResourceNotFoundException("invalid credential"));

     boolean matches = passwordEncoder.matches(loginRequestDto.getPassword(),user.getPassword());

     if(!matches){
          throw new ResourceNotFoundException("invalid credential");
     }

        String token = jwtUtil.generateToken(user.getId(),
                user.getEmail(),
                user.getRoles());

        return authMapper.tologinResponse(user,token);
    }

    @Override
    public UserResponseDto getById(Long id) {
     User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found with this id : "+ id));
        return userMapper.toDto(user);
    }
}
