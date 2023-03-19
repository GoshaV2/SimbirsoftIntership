package com.simbirsoft.intership.dto.request;

import com.simbirsoft.intership.model.enumaration.Permission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitingUserDto {
    private long userId;
    private Permission permission;
}
