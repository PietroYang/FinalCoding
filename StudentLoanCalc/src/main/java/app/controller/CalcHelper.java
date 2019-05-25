package app.controller;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.apache.poi.ss.formula.functions.Finance;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	
	public CalcHelper(double LoanAmount,double AdditionalPayment, int NbrOfYears, double InterestRate,Calendar startCal) {
		this.LoanAmount = LoanAmount;
		this.InterestRate = InterestRate;
		this.NbrOfYears = NbrOfYears;
		this.AdditionalPayment = AdditionalPayment;
		this.ratePerMonth = InterestRate/12;
		this.totalMonth=this.NbrOfYears*12;
		this.monthlyPayment=this.CalculatePMT();
		this.calculateTotalPaymentResult=monthlyPayment*totalMonth;
		CalculateTotalPayment(startCal);
	}
	private double ratePerMonth ;
	private double monthlyPayment;
	private double totalInterest=0;
	
	public double CalculatePMT() {
		double r = InterestRate/12;//ratePerMonth
		double n = NbrOfYears*12;//total month need to pay
		double p = LoanAmount;
		double f = 0;//future value
		boolean t = false;//type
		double MonthlyPayment = Math.abs(FinanceLib.pmt(r, n, p, f, t));
		return MonthlyPayment;
	}
	
	public double getCalculateTotalPayment() {
		return calculateTotalPaymentResult;
	}
	
	public java.util.List<SheetItems> getResultInTable(){
		return this.resutsInTable;
	}
	
	public double getTotalInterest() {
		return this.totalInterest;
	}
	
	private void CalculateTotalPayment(Calendar startCal) {
		

		this.resutsInTable=new ArrayList<>();

		double balance=this.LoanAmount;
		for(int i=0;i<totalMonth;i++) {
			double interest=balance*ratePerMonth;
			totalInterest += interest;
			double principal=monthlyPayment-interest;
			balance=balance-principal;
			startCal.add(Calendar.MONTH, 1);
			String dateStr=new SimpleDateFormat("yyyy-MM-dd").format(startCal.getTime());
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date =LocalDate.parse(dateStr, dateFormatter);
			SheetItems item= new SheetItems(i,date,monthlyPayment,0,interest,principal,balance);
			resutsInTable.add(item);
		}
		
//		double interest = 0;
//		double PPMT=0;
//		double TotalLoan= LoanAmount*InterestRate*NbrOfYears+LoanAmount;
//		
//		double pv = LoanAmount; //  pv=贷款的本金 pmt=等额还款 每月按揭金额（本金加利息）
//		while(PPMT<LoanAmount) {//PPMT + AdditionalPayment < pv;) { //ppmt=等额还款 每月按揭中本金偿还金额
//        //LoanAmount++; 每月的钱会减 balance会减少 月数增加
//			int col_paynum = col_paynum+1;
//			
//			double PMT = CalculatePMT();
//			double PPMT = PMT - CalculateInterest();
//			pv -= PPMT + AdditionalPayment;
//			interest += PMT-PPMT;
//			System.out.println(PPMT+AdditionalPayment);
//		}
//		Double FinalInterest = CalculateInterest();//
//		calculateTotalPaymentResult= interest + LoanAmount + FinalInterest;
		
	}
	
}