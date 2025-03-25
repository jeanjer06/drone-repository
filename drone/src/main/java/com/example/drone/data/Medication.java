package com.example.drone.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="MEDICATION")
public class Medication {
	@Id
	@Column(name="CODE")
    private String code;
	@Column(name="NAME")
    private String name;
	@Column(name="WEIGHT")
    private int weight;
	@Column(name="MED_PHOTO")
    private String image;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
