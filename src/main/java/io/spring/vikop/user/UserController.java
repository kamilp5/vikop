package io.spring.vikop.user;

import io.spring.vikop.user.dto.EditUserDetailsDto;
import io.spring.vikop.user.dto.NewUserDto;
import io.spring.vikop.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@Valid @RequestBody NewUserDto user) {
        return userService.registerUser(user);
    }

    @GetMapping("/login")
    public UserDto getLoggedUser() {
        return userService.getLoggedUserDto();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userService.getUserDtoById(userId);
    }

    @PutMapping("/user_details")
    @PreAuthorize("!isAnonymous()")
    public UserDto editUSerDetails(@Valid @RequestBody EditUserDetailsDto userDetails) {
        return userService.updateUserDetails(userDetails);
    }

}
