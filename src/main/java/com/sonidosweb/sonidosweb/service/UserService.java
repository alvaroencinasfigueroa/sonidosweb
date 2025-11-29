package com.sonidosweb.sonidosweb.service;

import com.sonidosweb.sonidosweb.dto.LoginRequest;
import com.sonidosweb.sonidosweb.dto.RegisterRequest;
import com.sonidosweb.sonidosweb.dto.UserResponse;

public interface UserService {
    UserResponse register(RegisterRequest request);
    UserResponse login(LoginRequest request);
    UserResponse getCurrentUser(String email);
}