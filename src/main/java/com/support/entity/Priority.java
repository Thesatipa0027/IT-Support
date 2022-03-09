package com.support.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Priority {

	@Id
	private long priority_id;

	private String priority_desc;

	public Priority() {
		super();
	}

	public Priority(long priority_id, String priority_desc) {
		super();
		this.priority_id = priority_id;
		this.priority_desc = priority_desc;
	}

	public long getPriority_id() {
		return priority_id;
	}

	public void setPriority_id(long priority_id) {
		this.priority_id = priority_id;
	}

	public String getPriority_desc() {
		return priority_desc;
	}

	public void setPriority_desc(String priority_desc) {
		this.priority_desc = priority_desc;
	}

}
