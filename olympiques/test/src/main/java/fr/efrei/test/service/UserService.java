package fr.efrei.test.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.efrei.test.UserRepository;
import fr.efrei.test.dto.UserDto;
import fr.efrei.test.model.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto createUser(UserDto userDTO, String password) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(userDTO.getRole());

        user = userRepository.save(user);

        userDTO.setId(user.getId());
        return userDTO;
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        UserDto userDTO = new UserDto ();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setRole(user.getRole());
            return userDTO;
        }).collect(Collectors.toList());
    }

    public UserDto updateUser(Long userId, UserDto userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        user = userRepository.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}