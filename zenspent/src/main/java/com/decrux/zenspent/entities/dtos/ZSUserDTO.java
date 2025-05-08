package com.decrux.zenspent.entities.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ZSUserDTO {

    private String username;
    private String email;
    private String firstName;
    private String lastName;

}
