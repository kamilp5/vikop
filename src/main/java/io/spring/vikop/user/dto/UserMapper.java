package io.spring.vikop.user.dto;

import io.spring.vikop.common.Mapper;
import io.spring.vikop.tag.Tag;
import io.spring.vikop.user.User;
import io.spring.vikop.user.UserDetails;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .avatarUrl(user.getUserDetails().getAvatarUrl())
                .email(user.getUserDetails().getEmail())
                .followedTags(user.getFollowedTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    public User toUser(NewUserDto newUserDto) {
        User user = new User();
        user.setUsername(newUserDto.getUsername());
        user.setPassword(newUserDto.getPassword());
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(newUserDto.getEmail());
        user.setUserDetails(userDetails);
        return user;
    }

}
