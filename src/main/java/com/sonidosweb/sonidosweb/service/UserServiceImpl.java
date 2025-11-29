package com.sonidosweb.sonidosweb.service;

import com.sonidosweb.sonidosweb.dto.LoginRequest;
import com.sonidosweb.sonidosweb.dto.RegisterRequest;
import com.sonidosweb.sonidosweb.dto.UserResponse;
import com.sonidosweb.sonidosweb.entity.User;
import com.sonidosweb.sonidosweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request) {
        // Verificar si el email ya existe
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Crear nuevo usuario
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Guardar en base de datos
        User savedUser = userRepository.save(user);

        // Retornar respuesta
        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.isHasAndroidLicense(),
                savedUser.isHasDesktopLicense()
        );
    }

    @Override
    public UserResponse login(LoginRequest request) {
        // Buscar usuario por email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        // Verificar contraseña
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Retornar respuesta
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isHasAndroidLicense(),
                user.isHasDesktopLicense()
        );
    }

    @Override
    public UserResponse getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isHasAndroidLicense(),
                user.isHasDesktopLicense()
        );
    }
}