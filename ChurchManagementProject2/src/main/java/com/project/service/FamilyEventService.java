package com.project.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.entity.Family;
import com.project.entity.FamilyEvent;
import com.project.entity.UserEvent;
import com.project.repository.FamilyEventRepository;
import com.project.repository.FamilyRepository;

@Service
public class FamilyEventService {

    @Autowired
    private FamilyEventRepository eventRepository;

    @Autowired
    private FamilyRepository familyRepository;

    public ResponseEntity<List<FamilyEvent>> createEventForFamily(Long familyId, FamilyEvent event) {
        Family family = familyRepository.findById(familyId).orElse(null);

        if (family == null) {
            return ResponseEntity.notFound().build();
        }

        event.setFamily(family);
        eventRepository.save(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(family.getEvents());
    }

    public List<FamilyEvent> getAllEvents() {
        return eventRepository.findAll();
    }

    public ResponseEntity<FamilyEvent> getEventById(Long eventId) {
        Optional<FamilyEvent> event = eventRepository.findById(eventId);
        return event.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<FamilyEvent> updateEvent(Long eventId, FamilyEvent updatedEvent) {
    	FamilyEvent existingEvent = eventRepository.findById(eventId).orElse(null);

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
        Optional<FamilyEvent> event = eventRepository.findById(eventId);

        if (event.isPresent()) {
            eventRepository.deleteById(eventId);
            return ResponseEntity.ok("Event with ID '" + eventId + "' deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
