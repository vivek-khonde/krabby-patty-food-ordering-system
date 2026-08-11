package com.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String image;
	private String description;
	private Boolean active;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int id, String title, String image, String description, Boolean active) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.description = description;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", title=" + title + ", image=" + image + ", description=" + description
				+ ", active=" + active + "]";
	}
	
	
}
