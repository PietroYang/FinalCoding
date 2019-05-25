package app.controller;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.apache.poi.ss.formula.functions.Finance;

import java.util.ArrayList;
import java.util.Calendar;



public class CalcHelper{
	
	
	private double LoanAmount;
	private double AdditionalPayment;
	private int NbrOfYears;
	private double InterestRate;
	
	
	private double totalMonth;
	private double calculateTotalPaymentResult;
	private java.util.List<SheetItems> resutsInTable;
	
	public CalcHelper(double LoanAmount,double AdditionalPayment, int NbrOfYears, double InterestRate) {
		this.LoanAmount = LoanAmount;
		this.InterestRate = InterestRate;
		this.NbrOfYears = NbrOfYears;
		this.AdditionalPayment = AdditionalPayment;
		
	}
	
	public double CalInterest() {
		double ratePerMonth = InterestRate/12;
		double interest = totalMonth*ratePerMonth;
		return interest;
//		interest= NbrOfYears*12*InterestRate  ??
// static public double pmt(double r, int nper, double pv, double fv, int type) {
//        return -r * (pv * Math.pow(1 + r, nper) + fv) / ((1 + r*type) * (Math.pow(1 + r, nper) - 1));
//	}
	
	}
	public double CalPMT() {
		double r = InterestRate/12;//ratePerMonth
		double n = NbrOfYears*12;//total month need to pay
		double p = LoanAmount;
		double f = 0;//future value
		boolean t = false;//type
		
		double MonthlyPayment = Math.abs(FinanceLib.pmt(r, n, p, f, t));
		return MonthlyPayment;
	}
	
//	public double getCalculateTotalPayment() {
//		return calculateTotalPaymentResult;
//	}
	
	public double CalculateTotalPayment() {
		double interest = 0;
		double PPMT = 0;
		double pv = LoanAmount;
		
		while(PPMT + AdditionalPayment < pv) {
			double PMT = CalculatePMT();
			PPMT = PMT - CalculateInterest(pv);
			pv -= PPMT + AdditionalPayment;
			interest += PMT-PPMT;
			System.out.println(PPMT+AdditionalPayment);
		}
		Double FinalInterest = CalculateInterest(pv);
		return interest + LoanAmount + FinalInterest;
	}
	
	public double CalculateTotalInterest() {
		double TotalInterest = CalculateTotalPayment() - LoanAmount;
		return TotalInterest;
	}
}

