package com.project.controller.eventcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.entity.UserEvent;
import com.project.service.UserEventService;

@RestController
@RequestMapping("/userevents")
public class UserEventController {

	@Autowired
	private UserEventService eventService;

	@PostMapping("/create/{userId}")
	public ResponseEntity<Object> createEventForUser(@PathVariable Long userId, @RequestBody UserEvent event) {
		try {
			ResponseEntity<List<UserEvent>> response = eventService.createEventForUser(userId, event);
			if (response.getStatusCode() == HttpStatus.CREATED) {
				return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with userId: " + userId);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to create event.");
		}
	}

	@GetMapping("/findAllEvents")
	public ResponseEntity<Object> getAllEvents() {
		try {
			List<UserEvent> events = eventService.getAllEvents();
			return ResponseEntity.ok(events);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to retrieve events.");
		}
	}

	@GetMapping("/findEventById/{eventId}")
	public ResponseEntity<Object> getEventById(@PathVariable Long eventId) {
		try {
			ResponseEntity<UserEvent> response = eventService.getEventById(eventId);
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
	public ResponseEntity<Object> updateEvent(@PathVariable Long eventId, @RequestBody UserEvent updatedEvent) {
		try {
			ResponseEntity<UserEvent> response = eventService.updateEvent(eventId, updatedEvent);
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
