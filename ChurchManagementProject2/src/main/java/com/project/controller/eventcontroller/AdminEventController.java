package com.project.controller.eventcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.entity.AdminEvent;
import com.project.service.AdminEventService;

@RestController
@RequestMapping("/Adminevents")
public class AdminEventController {

    @Autowired
    private AdminEventService eventService;

    @PostMapping("/create/{adminId}")
    public ResponseEntity<Object> createEventForAdmin(@PathVariable Long adminId, @RequestBody AdminEvent event) {
        try {
            ResponseEntity<List<AdminEvent>> response = eventService.createEventForAdmin(adminId, event);
            if (response.getStatusCode() == HttpStatus.CREATED) {
                return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin with ID '" + adminId + "' not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to create event - " + e.getMessage());
        }
    }

    @GetMapping("/findAllEvents")
    public ResponseEntity<Object> getAllEvents() {
        try {
            Iterable<AdminEvent> events = eventService.getAllEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to retrieve events - " + e.getMessage());
        }
    }

    @GetMapping("/findEventById/{eventId}")
    public ResponseEntity<Object> getEventById(@PathVariable Long eventId) {
        try {
            ResponseEntity<AdminEvent> response = eventService.getEventById(eventId);
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event with ID '" + eventId + "' not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to retrieve event - " + e.getMessage());
        }
    }

    @PutMapping("/updateEventById/{eventId}")
    public ResponseEntity<Object> updateEvent(@PathVariable Long eventId, @RequestBody AdminEvent updatedEvent) {
        try {
            ResponseEntity<AdminEvent> response = eventService.updateEvent(eventId, updatedEvent);
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event with ID '" + eventId + "' not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to update event - " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteEventById/{eventId}")
    public ResponseEntity<Object> deleteEvent(@PathVariable Long eventId) {
        try {
            ResponseEntity<String> response = eventService.deleteEvent(eventId);
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event with ID '" + eventId + "' not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to delete event - " + e.getMessage());
        }
    }
}
