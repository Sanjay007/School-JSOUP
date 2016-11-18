package org.schoo.api.model;

public class School {
	String name;
	String type;
	String district;
	String state;
	String address;
	String phone;
	String email;
	String website;
	String coed;
	String pincode;

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCoed() {
		return coed;
	}

	public void setCoed(String coed) {
		this.coed = coed;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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
				+ this.district + " State :" + this.state;

	}
	
	@Override
	public int hashCode(){
        System.out.println("In hashcode");
        int hash = 5;
        hash = 89  + (this.name != null ? this.name.hashCode() : 0);
        hash = 89  + (int) (this.address != null ? this.address.hashCode() : 0);
        hash = 89  + (this.state != null ? this.state.hashCode() : 0);
        hash = 89  + (this.district != null ? this.district.hashCode() : 0);
        hash = 89  + (this.phone != null ? this.phone.hashCode() : 0);
        hash = 89  + (this.pincode != null ? this.pincode.hashCode() : 0);
        hash = 89  + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }
	
	@Override
	public boolean equals(Object obs){
		System.out.println("Caling Equals");
		if(obs instanceof School){
			School ins=(School)obs;
		return ins.address == this.address && ins.name == this.name
				&& ins.district == this.district && ins.email == this.email
					&& ins.phone == this.phone && ins.pincode == this.pincode
					&& ins.state == this.state && ins.coed == this.coed;
		}else{return false;}
		
	}
	
	

}
