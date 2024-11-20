package com.reminder.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reminder.demo.dto.UserDto;
import com.reminder.demo.entity.Reminder;
import com.reminder.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Data
@Service
public class UserService {

    public UserService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    private  final ObjectMapper objectMapper;


        public UserDto getUser() {
        List<Reminder> reminders = new ArrayList<>();
        User user = new User(55L, "Hello", "Password", "OAUTH2", "email", reminders);
        return user.usersToDto(user, objectMapper);
    }

}