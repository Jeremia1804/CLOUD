package com.codex.tomobilina.mappers;

import com.codex.tomobilina.dtos.SignUpDto;
import com.codex.tomobilina.dtos.UserDto;
import com.codex.tomobilina.models.User;
import java.sql.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-10T09:33:43+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.username( user.getUsername() );
        userDto.email( user.getEmail() );
        userDto.sexe( user.getSexe() );

        return userDto.build();
    }

    @Override
    public User signUpToUser(SignUpDto signUpDto) {
        if ( signUpDto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( signUpDto.getUsername() );
        user.setEmail( signUpDto.getEmail() );
        if ( signUpDto.getDtn() != null ) {
            user.setDtn( new Date( signUpDto.getDtn().getTime() ) );
        }
        user.setSexe( signUpDto.getSexe() );

        return user;
    }
}
