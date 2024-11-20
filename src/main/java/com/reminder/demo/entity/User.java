package com.reminder.demo.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reminder.demo.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    public User() {
    }

    public User(Long id, String username, String password, String oauth2Provider, String email, List<Reminder> reminders) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.oauth2Provider = oauth2Provider;
        this.email = email;
        this.reminders = reminders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public UserDto usersToDto(User user, ObjectMapper objectMapper) {
        return objectMapper.convertValue(user, UserDto.class);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public String getOauth2Provider() {
        return oauth2Provider;
    }

    public void setOauth2Provider(String oauth2Provider) {
        this.oauth2Provider = oauth2Provider;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String oauth2Provider;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reminder> reminders;


}
