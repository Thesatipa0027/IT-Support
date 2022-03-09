package com.support.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Ticket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_genrator")
	@SequenceGenerator(name = "ticket_genrator", sequenceName = "ticket_seq")
	private long ticket_id;

	private int category_id;

	private int sub_category_id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "assignee_id", referencedColumnName = "admin_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@JsonIgnore
	private Admin assignee;

	private long reported_id;

	private String subject;

	@Lob
	private String description;

	private int status_id;

	private int priority_id;

	private String create_datetime;

	@Column(name = "last_modified")
	private String last_modified_datetime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket", cascade = { CascadeType.ALL })
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<Comment> commentList;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private User user;

	public Ticket() {
		super();
	}


	public Ticket(int category_id, int sub_category_id, String subject, String description, int priority_id) {
		super();
		this.category_id = category_id;
		this.sub_category_id = sub_category_id;
		this.subject = subject;
		this.description = description;
		this.priority_id = priority_id;
	}

	public Ticket(Admin assignee) {
		super();
		this.assignee = assignee;
	}

	public long getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getSub_category_id() {
		return sub_category_id;
	}

	public void setSub_category_id(int sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	public Admin getAssignee() {
		return assignee;
	}

	public void setAssignee(Admin assignee) {
		this.assignee = assignee;
	}

	public long getReported_id() {
		return reported_id;
	}

	public void setReported_id(long reported_id) {
		this.reported_id = reported_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getPriority_id() {
		return priority_id;
	}

	public void setPriority_id(int priority_id) {
		this.priority_id = priority_id;
	}

	public String getCreate_datetime() {
		return create_datetime;
	}

	public void setCreate_datetime(String create_datetime) {
		this.create_datetime = create_datetime;
	}

	public String getLast_modified_datetime() {
		return last_modified_datetime;
	}

	public void setLast_modified_datetime(String last_modified_datetime) {
		this.last_modified_datetime = last_modified_datetime;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

