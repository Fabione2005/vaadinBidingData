package com.fabione.clases;

public class People {

	String name;
	int age;
	String profession,nationality;
	
	public People(People p) {
		this.name = p.getName();
		this.age = p.getAge();
		this.profession = p.getProfession();
		this.nationality = p.getNationality();
	}
	
	public People() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Override
	public String toString() {
		return this.name+" "+this.age+" "+this.profession+" "+this.nationality;  
	}
	
	
	
	
}
