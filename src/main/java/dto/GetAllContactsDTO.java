package dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor

public class GetAllContactsDTO {

    List<ContactDTO> contacts;

}
