package org.schoo.api.model;

public class SchoolDetail {

	String address;
	String YearofEstab;
	String Board;
	String Classes;
	String Category;
	String Residential;
	String type;
	String PrePrimary;
	String Management;
	String Medium;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getYearofEstab() {
		return YearofEstab;
	}
	public void setYearofEstab(String yearofEstab) {
		YearofEstab = yearofEstab;
	}
	public String getBoard() {
		return Board;
	}
	public void setBoard(String board) {
		Board = board;
	}
	public String getClasses() {
		return Classes;
	}
	public void setClasses(String classes) {
		Classes = classes;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getResidential() {
		return Residential;
	}
	public void setResidential(String residential) {
		Residential = residential;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrePrimary() {
		return PrePrimary;
	}
	public void setPrePrimary(String prePrimary) {
		PrePrimary = prePrimary;
	}
	public String getManagement() {
		return Management;
	}
	public void setManagement(String management) {
		Management = management;
	}
	public String getMedium() {
		return Medium;
	}
	public void setMedium(String medium) {
		Medium = medium;
	}
	
	public String toString(){
		
		return "\n addr:" + this.address + " board:" + this.Board + " \ncategory:"
				+ this.Category + " class:" + this.Classes + "managemnt"
				+ this.Management + "\n medium" + this.Medium + " Pre-Prim "
				+ this.PrePrimary + " \nResident" + this.Residential + " type"
				+ this.type+" year of estb"+this.YearofEstab;
	}
	
}
