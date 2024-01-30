package com.project.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.entity.User;
import com.project.entity.UserEvent;
import com.project.repository.UserEventRepository;
import com.project.repository.UserRepository;

@Service
public class UserEventService {

    @Autowired
    private UserEventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<UserEvent>> createEventForUser(Long userId, UserEvent event) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        event.setUser(user);
        eventRepository.save(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(user.getEvents());
    }

    public List<UserEvent> getAllEvents() {
        return eventRepository.findAll();
    }

    public ResponseEntity<UserEvent> getEventById(Long eventId) {
        Optional<UserEvent> event = eventRepository.findById(eventId);
        return event.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<UserEvent> updateEvent(Long eventId, UserEvent updatedEvent) {
        UserEvent existingEvent = eventRepository.findById(eventId).orElse(null);

        if (existingEvent != null) {
            if (updatedEvent.getEventType() != null) {
                existingEvent.setEventType(updatedEvent.getEventType());
            }
            if (updatedEvent.getEventName() != null) {
                existingEvent.setEventName(updatedEvent.getEventName());
            }
            if (updatedEvent.getChurchName() != null) {
                existingEvent.setChurchName(updatedEvent.getChurchName());
            }
            if (updatedEvent.getEvent_date() != null) {
                existingEvent.setEvent_date(updatedEvent.getEvent_date());
            }
            if (updatedEvent.getEvent_time() != null) {
                existingEvent.setEvent_time(updatedEvent.getEvent_time());
            }
            if (updatedEvent.getDescription() != null) {
                existingEvent.setDescription(updatedEvent.getDescription());
            }

            return ResponseEntity.ok(eventRepository.save(existingEvent));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> deleteEvent(Long eventId) {
        Optional<UserEvent> event = eventRepository.findById(eventId);

        if (event.isPresent()) {
            eventRepository.deleteById(eventId);
            return ResponseEntity.ok("Event with ID '" + eventId + "' deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
