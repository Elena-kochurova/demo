package com.reminder.demo.controller;


import com.reminder.demo.dto.UserDto;
import com.reminder.demo.entity.Reminder;
import com.reminder.demo.repository.ReminderRepository;
import com.reminder.demo.service.ReminderService;
import com.reminder.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/users/{userId}/reminders")
public class ReminderController {

    public ReminderController(ReminderService reminderService, UserService userService) {
        this.reminderService = reminderService;
        this.userService = userService;
    }

    @Autowired
    private ReminderService reminderService;
    private UserService userService;

    @GetMapping("/getAllReminders")
    public Page<Reminder> getAllRemindersByUser(
            @PathVariable Long userId,  // берёт данные {userId}
            Pageable pageable) {
        return reminderService.getAllRemindersByUser(userId, pageable);
    }



    @GetMapping("/search")
    public Page<Reminder> searchReminders(
            @PathVariable Long userId,
            @RequestParam(required = false) String title, // вводимые параметры с постмана
            @RequestParam(required = false) LocalDateTime date,
            Pageable pageable
    )
    {
        return reminderService.searchReminders(userId, title, date, pageable);
    }


    @GetMapping("/getAllRemindersBySort")
    public Page<Reminder> getAllRemindersByUserSort(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "title") String sortBy, // defaultValue = "title" - если в постмане не указать параметр,по умолчанию будет title
            @RequestParam(defaultValue = "asc") String sortOrder,
            Pageable pageable)
    {

        Sort sort = Sort.by(sortBy);
        if("desc".equalsIgnoreCase(sortOrder)) {
            sort = sort.descending();  // обратная сотрировка
        } else {
            sort = sort.ascending(); // по возрастанию
        }

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return reminderService.getAllRemindersByUser(userId, pageable);
    }


    @PostMapping // создаю
    public ResponseEntity<Reminder> addReminder(
            @PathVariable Long userId,
            @RequestBody Reminder reminder) // параметр через постман в виде джейсон (вкладка body)
    {
        Reminder reminderSaved = reminderService.addReminderToUser(userId, reminder);
        return new ResponseEntity<>(reminderSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{reminderId}")
    public ResponseEntity<Reminder> updateReminder(
            @PathVariable Long userId,
            @PathVariable Long reminderId,
            @RequestBody Reminder reminderUpdate)
    {
        Reminder updateReminder = reminderService.updateReminder(userId, reminderId, reminderUpdate);
        return ResponseEntity.ok(updateReminder);
    }

    @DeleteMapping("/{reminderId}")
    public ResponseEntity<Void> deleteReminder(
            @PathVariable Long userId,
            @PathVariable Long reminderId)
    {
        reminderService.deleteReminder(userId, reminderId);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/getUser")
    public UserDto getUserDetail() {
        return userService.getUser();
    }

}
