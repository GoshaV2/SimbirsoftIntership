package com.simbirsoft.intership.service.constants;

import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.model.enumaration.Role;

import java.util.Set;

public enum Users {
    User1(User.builder()
            .id(1L).email("test1@mail.ru").fullName("Иван Иванович").roles(Set.of(Role.USER))
            .build()
    ),
    User2(User.builder()
            .id(2L).email("test2@mail.ru").fullName("Данил Иванович").roles(Set.of(Role.USER))
            .build()
    ),
    User3(User.builder()
            .id(3L).email("test3@mail.ru").fullName("Юрий Иванович").roles(Set.of(Role.USER))
            .build()
    ),
    User4(User.builder()
            .id(4L).email("test4@mail.ru").fullName("Сергей Иванович").roles(Set.of(Role.USER))
            .build()
    );
    private User user;

    Users(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
