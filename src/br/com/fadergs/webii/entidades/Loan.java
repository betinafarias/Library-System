package br.com.fadergs.webii.entidades;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Loan {
	private Integer id;
	private Student student;
	private Book book;
	private java.util.Date dateLoan;
	private java.util.Date dateDevolution;
	private java.util.Date dateExpected;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.util.Date getDateLoan() {
		return dateLoan;
	}
	public void setDateLoan(java.util.Date date_loan) {
		this.dateLoan = date_loan;
	}
	public java.util.Date getDateDevolution() {
		return dateDevolution;
	}
	public void setDateDevolution(java.util.Date dateDevolution) {
		this.dateDevolution = dateDevolution;
	}
	public java.util.Date getDateExpected() {
		return dateExpected;
	}
	public void setDateExpected(java.util.Date dateExpected) {
		this.dateExpected = dateExpected;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public String convertDateToString(java.util.Date date) {
		if(date != null) {
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yy");
			return s.format(date);				
		}
		else {
			return "";
		}
	}
	public void setDateLoan1(java.util.Date date_loan) {
		// TODO Auto-generated method stub
		this.dateLoan = (Date) date_loan;
	}


}
