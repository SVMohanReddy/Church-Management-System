package com.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Church {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int church_id;
	private String church_name;
	private String church_father_name;
	private String church_location;
	private long church_capacity;

	public Church()
	{
		
	}
	
	public Church(String church_name, String church_father_name, String church_location, long church_capacity) {
		this.church_name = church_name;
		this.church_father_name = church_father_name;
		this.church_location = church_location;
		this.church_capacity = church_capacity;
	}

	public int getChurch_id() {
		return church_id;
	}

	public void setChurch_id(int church_id) {
		this.church_id = church_id;
	}

	public String getChurch_name() {
		return church_name;
	}

	public void setChurch_name(String church_name) {
		this.church_name = church_name;
	}

	public String getChurch_father_name() {
		return church_father_name;
	}

	public void setChurch_father_name(String church_father_name) {
		this.church_father_name = church_father_name;
	}

	public String getChurch_location() {
		return church_location;
	}

	public void setChurch_location(String church_location) {
		this.church_location = church_location;
	}

	public long getChurch_capacity() {
		return church_capacity;
	}

	public void setChurch_capacity(long church_capacity) {
		this.church_capacity = church_capacity;
	}

	@Override
	public String toString() {
		return "Church [church_id=" + church_id + ", church_name=" + church_name + ", church_father_name="
				+ church_father_name + ", church_location=" + church_location + ", church_capacity=" + church_capacity
				+ "]";
	}

}
