package com.support.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "admin_team")
public class Admin implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_generator")
	@SequenceGenerator(name = "admin_generator", sequenceName = "admin_seq", initialValue = 1000)
	private long admin_id;
	
	private String name;
	
	private String email;

	public Admin() {
		super();
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assignee", cascade = { CascadeType.ALL })
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonBackReference
	private List<Ticket> ticketlist;

	public Admin(long admin_id) {
		super();
		this.admin_id = admin_id;
	}

	public Admin(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public long getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(long admin_id) {
		this.admin_id = admin_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Ticket> getTicketlist() {
		return ticketlist;
	}

	public void setTicketlist(List<Ticket> ticketlist) {
		this.ticketlist = ticketlist;
	}
	
}
