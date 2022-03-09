package com.support.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Status {

	@Id
	private long status_id;

	private String status_desc;

	public Status() {
		super();
	}

	public Status(long status_id, String status_desc) {
		super();
		this.status_id = status_id;
		this.status_desc = status_desc;
	}

	public long getStatus_id() {
		return status_id;
	}

	public void setStatus_id(long status_id) {
		this.status_id = status_id;
	}

	public String getStatus_desc() {
		return status_desc;
	}

	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}

}
