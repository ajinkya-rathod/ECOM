package User_Service.User_Service.mapper;

import User_Service.User_Service.dto.LoginResponseDto;
import User_Service.User_Service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public LoginResponseDto tologinResponse(User user,String token){
           return new LoginResponseDto(token,"Bearer", user.getId(),user.getEmail(),user.getName());
    }


}
