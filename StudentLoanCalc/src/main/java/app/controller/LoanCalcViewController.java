package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	 @FXML
	    private Button button_calculate;

	    @FXML
	    private Label lblLoanAmmount;

	    @FXML
	    private Label lblInterestRate;

	    @FXML
	    private Label lblTermOfLoanYears;

	    @FXML
	    private Label lblFirstPaymentDate;

	    @FXML
	    private Label lblAdditionalPayment;

	    @FXML
	    private TextField LoanAmount;

	    @FXML
	    private TextField InterestRate;

	    @FXML
	    private TextField NbrOfYears;

	    @FXML
	    private DatePicker PaymentStartDate;

	    @FXML
	    private TextField AdditionalPayment;

	    @FXML
	    private Label TotalPayments;

	    @FXML
	    private Label TotalInterest;

	    @FXML
	    private Label lblTotalPayemnts;

	    @FXML
	    private Label lblTotalInterest;

	    @FXML
	    private TableColumn<SheetItems, String> col_paynum;

	    @FXML
	    private TableColumn<SheetItems, String> col_duedate; 

	    @FXML
	    private TableColumn<SheetItems, String> col_payment;

	    @FXML
	    private TableColumn<SheetItems, String> col_additionalPayment;

	    @FXML
	    private TableColumn<SheetItems, String> col_interest;

	    @FXML
	    private TableColumn<SheetItems, String> col_principle;

	    @FXML
	    private TableColumn<SheetItems, String> col_balance;
	    
	    @FXML
	    private TableView<SheetItems> tableid;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
		this.col_paynum.setCellValueFactory(new PropertyValueFactory<SheetItems, String>("col_paynum"));
		this.col_duedate.setCellValueFactory(new PropertyValueFactory<SheetItems, String>("col_duedate"));
		this.col_payment.setCellValueFactory(new PropertyValueFactory<SheetItems, String>("col_payment"));
		this.col_additionalPayment.setCellValueFactory(new PropertyValueFactory<SheetItems, String>("col_additionalPayment"));
		this.col_interest.setCellValueFactory(new PropertyValueFactory<SheetItems, String>("col_interest"));
		this.col_principle.setCellValueFactory(new PropertyValueFactory<SheetItems, String>("col_principle"));
		this.col_balance.setCellValueFactory(new PropertyValueFactory<SheetItems, String>("col_balance"));
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 * @throws ParseException 
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) throws ParseException {
		
		
		String LoanAmountStr=this.LoanAmount.getText();
		double loan=Double.parseDouble(LoanAmountStr);
		
		
		String InterestRateStr=this.InterestRate.getText();
		double rate=Double.parseDouble(InterestRateStr);
		
		
		String yearStr=this.NbrOfYears.getText();
		int year=Integer.parseInt(yearStr);//"15"  15
				
		
		LocalDate localDate =this.PaymentStartDate.getValue();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dataStr=dateFormatter.format(localDate);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date=sdf.parse(dataStr);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		
		String AdditionalPayment=this.AdditionalPayment.getText();
		double addition=Double.parseDouble(AdditionalPayment);
		
		
		CalcHelper calHe=new CalcHelper(loan,addition,year, rate,cal);
		String resultLblTotalPayemnts=calHe.getCalculateTotalPayment()+"";//Fin
		String resultlblTotalInterest=calHe.getTotalInterest()+"";//
		List<SheetItems> resutsInTable=calHe.getResultInTable();// =Finance .xxx 
		

		this.lblTotalPayemnts.setText(resultLblTotalPayemnts);
		this.lblTotalInterest.setText(resultlblTotalInterest);
		
		this.tableid.getItems().clear();
		this.tableid.getItems().addAll(resutsInTable);
		
	}
	

}
