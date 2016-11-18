package org.schoo.api.model;

public class School2 {
	String name;
	String type;
	String district;
	String state;
	private SchoolDetail schoolDet;
	

	public SchoolDetail getSchoolDet() {
		return schoolDet;
	}

	public void setSchoolDet(SchoolDetail schoolDet) {
		this.schoolDet = schoolDet;
	}

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
				+ this.district + " State :" + this.state+this.schoolDet.toString();

	}

}
