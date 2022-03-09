package com.support.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Comment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_genrator")
	@SequenceGenerator(name="comment_genrator", sequenceName = "comment_seq")
	private long comment_id;

	private String message;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Ticket ticket;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private User user;

	public Comment() {
		super();
	}

	public Comment(String message) {
		super();
		this.message = message;
	}

	public long getId() {
		return comment_id;
	}
	public void setId(long id) {
		this.comment_id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
