package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class ErrorDTO {

    int status;
    String error;
    String message;


}
