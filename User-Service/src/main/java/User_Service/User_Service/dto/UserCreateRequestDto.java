package User_Service.User_Service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "valid email is required")
    @NotBlank(message = "email is required ")
    private String email;

    @NotBlank(message = "password required ")
    @Size(min = 6,message = "Password must be 6 characters")
    private String password;
    private String phone;
}
