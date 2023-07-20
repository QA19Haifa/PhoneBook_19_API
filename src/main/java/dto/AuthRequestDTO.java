package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor

public class AuthRequestDTO {

    String username;
    String password;

}
