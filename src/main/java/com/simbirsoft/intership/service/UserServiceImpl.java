package com.simbirsoft.intership.service;

import com.simbirsoft.intership.exception.NotFoundUserException;
import com.simbirsoft.intership.model.User;
import com.simbirsoft.intership.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException(userId));
    }
}
