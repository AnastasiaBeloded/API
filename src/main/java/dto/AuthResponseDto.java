package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//{
//        "token": "string"
//        }
@Setter
@Getter
@ToString
@Builder
public class AuthResponseDto {
    private String token;
}
