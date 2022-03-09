package com.support.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Category {
	
	@Id
	private long category_id;

	private String category_desc;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = { CascadeType.ALL })
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<Sub_Category> sub_category_list;

	public Category() {
		super();
	}

	public Category(String category_desc, List<Sub_Category> sub_category_list) {
		super();
		this.category_desc = category_desc;
		this.sub_category_list = sub_category_list;
	}

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public String getCategory_desc() {
		return category_desc;
	}

	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}

	public List<Sub_Category> getSub_category_list() {
		return sub_category_list;
	}

	public void setSub_category_list(List<Sub_Category> sub_category_list) {
		this.sub_category_list = sub_category_list;
	}

}
