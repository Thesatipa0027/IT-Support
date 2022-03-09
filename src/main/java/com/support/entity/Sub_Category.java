package com.support.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Sub_Category {

	@Id
	private long sub_category_id;

	private String sub_category_desc;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "category_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Category category;

	public Sub_Category() {
		super();
	}

	public Sub_Category(long sub_category_id, String sub_category_desc, Category category) {
		super();
		this.sub_category_id = sub_category_id;
		this.sub_category_desc = sub_category_desc;
		this.category = category;
	}

	public long getSub_category_id() {
		return sub_category_id;
	}

	public void setSub_category_id(long sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	public String getSub_category_desc() {
		return sub_category_desc;
	}

	public void setSub_category_desc(String sub_category_desc) {
		this.sub_category_desc = sub_category_desc;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
