package dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class ContactDTO {

    String id;
    String name;
    String lastName;
    String phone;
    String email;
    String address;
    String description;

}
