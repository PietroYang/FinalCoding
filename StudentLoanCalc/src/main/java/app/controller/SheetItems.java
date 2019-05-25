package app.controller;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

public class SheetItems {

	SimpleStringProperty col_paynum;
	SimpleStringProperty col_duedate;
	SimpleStringProperty col_payment;
	SimpleStringProperty col_additionalPayment;
	SimpleStringProperty col_interest;
	SimpleStringProperty col_principle;
	SimpleStringProperty col_balance;
	
	

	public SheetItems(int col_paynum, LocalDate col_duedate, double col_payment, double col_additionalPayment, double col_interest, double col_principle, double col_balance) {
		this.col_paynum = new SimpleStringProperty(String.valueOf(col_paynum));
        this.col_duedate = new SimpleStringProperty(col_duedate.toString());
        this.col_payment = new SimpleStringProperty(String.valueOf(col_payment));
        this.col_additionalPayment = new SimpleStringProperty(String.valueOf(col_additionalPayment));
        this.col_interest = new SimpleStringProperty(String.valueOf(col_interest));
        this.col_principle = new SimpleStringProperty(String.valueOf(col_principle));
        this.col_balance = new SimpleStringProperty(String.valueOf(col_balance));
	    }



	public SimpleStringProperty getCol_paynum() {
		return col_paynum;
	}



	public void setCol_paynum(SimpleStringProperty col_paynum) {
		this.col_paynum = col_paynum;
	}



	public SimpleStringProperty getCol_duedate() {
		return col_duedate;
	}



	public void setCol_duedate(SimpleStringProperty col_duedate) {
		this.col_duedate = col_duedate;
	}



	public SimpleStringProperty getCol_payment() {
		return col_payment;
	}



	public void setCol_payment(SimpleStringProperty col_payment) {
		this.col_payment = col_payment;
	}



	public SimpleStringProperty getCol_additionalPayment() {
		return col_additionalPayment;
	}



	public void setCol_additionalPayment(SimpleStringProperty col_additionalPayment) {
		this.col_additionalPayment = col_additionalPayment;
	}



	public SimpleStringProperty getCol_interest() {
		return col_interest;
	}



	public void setCol_interest(SimpleStringProperty col_interest) {
		this.col_interest = col_interest;
	}



	public SimpleStringProperty getCol_principle() {
		return col_principle;
	}



	public void setCol_principle(SimpleStringProperty col_principle) {
		this.col_principle = col_principle;
	}



	public SimpleStringProperty getCol_balance() {
		return col_balance;
	}



	public void setCol_balance(SimpleStringProperty col_balance) {
		this.col_balance = col_balance;
	}
		
	}

	
	
	

