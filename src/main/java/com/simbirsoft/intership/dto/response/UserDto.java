package com.simbirsoft.intership.dto.response;

import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
public class UserDto {
    private Long id;
    private String email;
    private String fullName;
    private Set<Role> roles;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roles(user.getRoles())
                .build();
    }
}
