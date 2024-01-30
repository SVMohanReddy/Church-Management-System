package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.entity.GroupEvent;
import com.project.entity.Groupp;
import com.project.repository.GroupEventRepository;
import com.project.repository.GroupRepository;

@Service
public class GroupEventService {

    @Autowired
    private GroupEventRepository eventRepository;

    @Autowired
    private GroupRepository groupRepository;

    public ResponseEntity<List<GroupEvent>> createEventForGroup(Long groupId, GroupEvent event) {
        Groupp group = groupRepository.findById(groupId).orElse(null);

        if (group == null) {
            return ResponseEntity.notFound().build();
        }

        event.setGroupp(group);
        eventRepository.save(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(group.getEvents());
    }

    public List<GroupEvent> getAllEvents() {
        return eventRepository.findAll();
    }

    public ResponseEntity<GroupEvent> getEventById(Long eventId) {
        Optional<GroupEvent> event = eventRepository.findById(eventId);
        return event.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<GroupEvent> updateEvent(Long eventId, GroupEvent updatedEvent) {
        GroupEvent existingEvent = eventRepository.findById(eventId).orElse(null);

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
        Optional<GroupEvent> event = eventRepository.findById(eventId);

        if (event.isPresent()) {
            eventRepository.deleteById(eventId);
            return ResponseEntity.ok("Event with ID '" + eventId + "' deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
