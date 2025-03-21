package ru.itis.roadhelp.services;

import ru.itis.roadhelp.entity.User;

public interface UserService {
    User getUserById(Long userId);
}
