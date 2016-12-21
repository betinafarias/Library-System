package br.com.fadergs.webii.entidades;

public class Book {
	private Integer id;
	private String title;
	private String publisher;
	private double price;
	private Category category;
	private Library library;
	private Boolean isAvailable;
	private Loan loan;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Library getLibrary() {
		return library;
	}

	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	public void setLibrary(Library lib) {
		this.library = lib;
		// TODO Auto-generated method stub
		
	}




}
