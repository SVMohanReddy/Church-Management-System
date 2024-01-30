package com.project.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GroupEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventId;
	private String eventType;
	private String eventName;
	private String churchName;
	private LocalDate event_date;
	private LocalTime event_time;
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Groupp groupp;

	private GroupEvent() {
	}

	public GroupEvent(String eventType, String eventName, String churchName, LocalDate event_date, LocalTime event_time,
			String description) {
		super();
		this.eventType = eventType;
		this.eventName = eventName;
		this.churchName = churchName;
		this.event_date = event_date;
		this.event_time = event_time;
		this.description = description;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public LocalDate getEvent_date() {
		return event_date;
	}

	public void setEvent_date(LocalDate event_date) {
		this.event_date = event_date;
	}

	public LocalTime getEvent_time() {
		return event_time;
	}

	public void setEvent_time(LocalTime event_time) {
		this.event_time = event_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Groupp getGroupp() {
		return groupp;
	}

	public void setGroupp(Groupp groupp) {
		this.groupp = groupp;
	}

}
