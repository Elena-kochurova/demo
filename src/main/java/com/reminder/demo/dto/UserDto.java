package com.reminder.demo.dto;

public record UserDto (
        String username,
        String oauth2Provider,
        String email
) {

}
