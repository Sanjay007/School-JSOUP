package org.schoo.api.model;

public class School {
	String name;
	String type;
	String district;
	String state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String toString() {

		return "Name:" + this.name + " Type :" + this.type + " District :"
				+ this.district + " Statte :" + this.state;

	}

}
