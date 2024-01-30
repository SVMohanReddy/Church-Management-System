package com.project.controller.eventcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.entity.GroupEvent;
import com.project.service.GroupEventService;

@RestController
@RequestMapping("/Groupevents")
public class GroupEventController {

    @Autowired
    private GroupEventService eventService;

    @PostMapping("/create/{groupId}")
    public ResponseEntity<Object> createEventForGroup(@PathVariable Long groupId, @RequestBody GroupEvent event) {
        try {
            ResponseEntity<List<GroupEvent>> response = eventService.createEventForGroup(groupId, event);
            if (response.getStatusCode() == HttpStatus.CREATED) {
                return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group not found with groupId: " + groupId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to create event.");
        }
    }

    @GetMapping("/findAllEvents")
    public ResponseEntity<Object> getAllEvents() {
        try {
            List<GroupEvent> events = eventService.getAllEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to retrieve events.");
        }
    }

    @GetMapping("/findEventById/{eventId}")
    public ResponseEntity<Object> getEventById(@PathVariable Long eventId) {
        try {
            ResponseEntity<GroupEvent> response = eventService.getEventById(eventId);
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found with eventId: " + eventId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to retrieve event.");
        }
    }

    @PutMapping("/updateEventById/{eventId}")
    public ResponseEntity<Object> updateEvent(@PathVariable Long eventId, @RequestBody GroupEvent updatedEvent) {
        try {
            ResponseEntity<GroupEvent> response = eventService.updateEvent(eventId, updatedEvent);
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found with eventId: " + eventId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to update event.");
        }
    }

    @DeleteMapping("/deleteEventById/{eventId}")
    public ResponseEntity<Object> deleteEvent(@PathVariable Long eventId) {
        try {
            ResponseEntity<String> response = eventService.deleteEvent(eventId);
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found with eventId: " + eventId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to delete event.");
        }
    }
}
