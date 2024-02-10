package com.codex.tomobilina.mappers;

import com.codex.tomobilina.dtos.SignUpDto;
import com.codex.tomobilina.dtos.UserDto;
import com.codex.tomobilina.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
