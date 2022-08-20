package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//{
//        "email": "string",
//        "password": "string"
//        }
@Setter
@Getter
@ToString
@Builder
public class AuthRequestDto {
    private String email;
    private String password;
}
