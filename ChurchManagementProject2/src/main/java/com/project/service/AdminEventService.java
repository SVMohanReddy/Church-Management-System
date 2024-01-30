package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.entity.Admin;
import com.project.entity.AdminEvent;
import com.project.entity.GroupEvent;
import com.project.repository.AdminEventRepository;
import com.project.repository.AdminRepository;

@Service
public class AdminEventService {

    @Autowired
    private AdminEventRepository adminEventRepository;

    @Autowired
    private AdminRepository adminRepository;

    public ResponseEntity<List<AdminEvent>> createEventForAdmin(Long adminId, AdminEvent event) {
        Admin admin = adminRepository.findById(adminId).orElse(null);

        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        event.setAdmin(admin);
        adminEventRepository.save(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(admin.getEvents());
    }

    public Iterable<AdminEvent> getAllEvents() {
        return adminEventRepository.findAll();
    }

    public ResponseEntity<AdminEvent> getEventById(Long eventId) {
        AdminEvent event = adminEventRepository.findById(eventId).orElse(null);

        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<AdminEvent> updateEvent(Long eventId, AdminEvent updatedEvent) {
        AdminEvent existingEvent = adminEventRepository.findById(eventId).orElse(null);

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

            return ResponseEntity.ok(adminEventRepository.save(existingEvent));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<String> deleteEvent(Long eventId) {
        if (adminEventRepository.existsById(eventId)) {
            adminEventRepository.deleteById(eventId);
            return ResponseEntity.ok("Event with ID '" + eventId + "' deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found with eventId: " + eventId);
        }
    }
}
