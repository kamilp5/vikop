package io.spring.vikop.user;

import io.spring.vikop.user.dto.EditUserDetailsDto;
import io.spring.vikop.user.dto.NewUserDto;
import io.spring.vikop.user.dto.UserDto;
import io.spring.vikop.user.dto.UserMapper;
import io.spring.vikop.user.exception.UserAlreadyExistsException;
import io.spring.vikop.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper mapper;
    private static final String DEFAULT_AVATAR = "https://forums.bad-dragon.com/download/file.php?avatar=35758_1449693083.png";

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    public UserDto registerUser(NewUserDto newUser) {
        isUsernameAvailable(newUser.getUsername());
        isEmailAvailable(newUser.getEmail());
        UserRole role = roleRepository.findByRole(RoleType.ROLE_USER);

        User user = mapper.toUser(newUser);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.getRoles().add(role);
        user.getUserDetails().setAvatarUrl(DEFAULT_AVATAR);
        userRepository.save(user);
        return mapper.toDto(user);
    }

    private void isUsernameAvailable(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User with username: " + username + " already exists");
        }
    }

    private void isEmailAvailable(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User with email: " + email + " already exists");
        }
    }

    public boolean isUserLogged() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !auth.getName().equals("anonymousUser");
    }

    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.getByUsername(auth.getName());
    }

    UserDto getLoggedUserDto() {
        return mapper.toDto(getLoggedUser());
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    UserDto getUserDtoById(Long id) {
        return mapper.toDto(getUserById(id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public UserDto updateUserDetails(EditUserDetailsDto userDetails) {
        User userToEdit = getLoggedUser();
        isEmailAvailable(userDetails.getEmail());
        userToEdit.getUserDetails().setEmail(userDetails.getEmail());
        userToEdit.getUserDetails().setAvatarUrl(userDetails.getAvatarUrl());

        userRepository.save(userToEdit);
        return mapper.toDto(userToEdit);
    }
}
