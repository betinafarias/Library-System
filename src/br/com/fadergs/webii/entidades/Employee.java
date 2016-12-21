package br.com.fadergs.webii.entidades;

public class Employee {
	private Integer id;
	private String name;
	private String address;
	private Integer phone;
	private double salary;
	private Integer idLibrary;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Integer getIdLibrary() {
		return idLibrary;
	}
	public void setIdLibrary(Integer idLibrary) {
		this.idLibrary = idLibrary;
	}
}
