package com.simbirsoft.intership.dto.response;

import com.simbirsoft.intership.model.ProjectPermission;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Permission;
import com.simbirsoft.intership.model.enumaration.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
public class UserWithPermissionDto {
    private Long id;
    private String email;
    private String fullName;
    private Set<Permission> permissions;
    private Set<Role> roles;

    public static UserWithPermissionDto from(ProjectPermission projectPermission) {
        User user = projectPermission.getUser();
        return UserWithPermissionDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .permissions(projectPermission.getPermissions())
                .roles(user.getRoles())
                .build();
    }
}
