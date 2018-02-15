import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//basic elements for Window
		JFrame win = new JFrame("Bank of Ireland");
		win.setLayout(new BorderLayout(10,10));
		Panel leftPanel = new Panel();
		Panel rightPanel = new Panel();
		Panel botPanel = new Panel();
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1= new JMenu("Accounts");
		JMenu menu2= new JMenu("Transactions");
		JMenuItem item1= new JMenuItem("Open Account");
		JMenuItem item2= new JMenuItem("Account Details");
		JMenuItem item3= new JMenuItem("Deposit");
		JMenuItem item4= new JMenuItem("Withdrawal");
		JMenuItem item5= new JMenuItem("Balance");
				
		//panel to hold views
		JPanel viewPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		// panels for each view

		JPanel openPanel = new JPanel(new GridLayout(15,15, 5, 5));
		JPanel detailsPanel = new JPanel(new GridLayout(15,1, 5, 5));
		JPanel depositPanel = new JPanel(new GridLayout(15,3, 5, 5));
		JPanel withdrawPanel = new JPanel(new GridLayout(15,3, 5, 5));
		JPanel balancePanel = new JPanel(new GridLayout(15,3, 5, 5));
		
		//set panels invisible
		openPanel.setVisible(false);
		detailsPanel.setVisible(false);
		depositPanel.setVisible(false);
		withdrawPanel.setVisible(false);
		balancePanel.setVisible(false);
		
		
		// labels for each Panel
		JLabel OPEN = new JLabel("OPEN ACCOUNT");
		JLabel DETAILS = new JLabel("ACCOUNT DETAILS");
		JLabel DEPOSIT = new JLabel("MAKE A DEPOSIT");
		JLabel WITHDRAW = new JLabel("MAKE A WITHRAWAL");
		JLabel BALANCE = new JLabel("CHECK BALANCE");
		
		// ADD LABELS TO PANELS
		openPanel.add(OPEN);
		detailsPanel.add(DETAILS);
		depositPanel.add(DEPOSIT);
		withdrawPanel.add(WITHDRAW);
		balancePanel.add(BALANCE);
		
		
		//elements to be added to views
		///////Field and Labels
		JLabel nameFieldLabel = new JLabel("Name");
		JTextField nameField = new JTextField(20);
		JLabel AddressFieldLabel = new JLabel("Address");
		JTextField addressField = new JTextField(100);
		
		///////RadioGroups and Listeners
		RadioEvent e1 = new RadioEvent();
		JPanel typeRadio = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel typeLabel = new JLabel("Choose Acct Type");
		JPanel genderRadio = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel genderLabel = new JLabel("Choose Gender Type");
		typeRadio.add(typeLabel);
		genderRadio.add(genderLabel);
		ButtonGroup typeGroup = new ButtonGroup();
		JRadioButton rbutton;
		typeGroup.add(rbutton = new JRadioButton("Savings"));
		rbutton.addItemListener(e1);
		typeRadio.add(rbutton);
		typeGroup.add(rbutton = new JRadioButton("Current"));
		rbutton.addItemListener(e1);
		typeRadio.add(rbutton);
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(rbutton = new JRadioButton("Male"));
		rbutton.addItemListener(e1);
		genderRadio.add(rbutton);
		genderGroup.add(rbutton = new JRadioButton("Female"));
		rbutton.addItemListener(e1);
		genderRadio.add(rbutton);
		
		/////end section for elements to be added to views
		
		// add elements to views
		//// openPanel view
		JPanel oaBTN = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JButton Open = new JButton("Create Account");
		openPanel.add(nameFieldLabel);
		openPanel.add(nameField);
		openPanel.add(AddressFieldLabel);
		openPanel.add(addressField);
		openPanel.add(genderRadio);
		openPanel.add(typeRadio);
		oaBTN.add(Open);
		openPanel.add(oaBTN);
		// action listener for "Create Account" Button
		Open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String n = nameField.getText();
				String a = addressField.getText();
				ConToDB C = new ConToDB();
				C.createAccount(n, a);
				
			}
			
		});
		///// deatailsPanel
		JPanel adBTN = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JButton Details = new JButton("See Account Details");
		JLabel acctFieldLabel = new JLabel("Account Number");
		JTextField accountField = new JTextField(5);
		JLabel nameFieldLabel2 = new JLabel("Name");
		JTextField nameField2 = new JTextField(20);
		JLabel AddressFieldLabel2 = new JLabel("Address");
		JTextField addressField2 = new JTextField(100);
		JLabel typeFieldLabel = new JLabel("Account Type");
		JTextField typeField = new JTextField(1);
		JLabel genderFieldLabel = new JLabel("Gender");
		JTextField genderField = new JTextField(1);
		JLabel balanceFieldLabel = new JLabel("Balance");
		JTextField balanceField = new JTextField(8);
		adBTN.add(Details);
		// action Listener for "See Account Details" button in detailsPanel
		Details.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = accountField.getText();
				ConToDB C = new ConToDB();
				C.getDetails(accountField, nameField2, addressField2, balanceField);
				if ((a.startsWith("s"))||(a.startsWith("S"))) {
					typeField.setText("Savings");
				}
				if ((a.startsWith("c"))||(a.startsWith("C"))) {
					typeField.setText("Current");
				}
				if ((a.startsWith("f", 1))||(a.startsWith("F", 1))){
					genderField.setText("Female");
				}
				if ((a.startsWith("m", 1))||(a.startsWith("M", 1))){
					genderField.setText("Male");
				}
				
			}
			
		});
		detailsPanel.add(acctFieldLabel);
		detailsPanel.add(accountField);
		detailsPanel.add(adBTN);
		detailsPanel.add(nameFieldLabel2);
		detailsPanel.add(nameField2);
		detailsPanel.add(AddressFieldLabel2);
		detailsPanel.add(addressField2);
		detailsPanel.add(typeFieldLabel);
		detailsPanel.add(typeField);
		detailsPanel.add(genderFieldLabel);
		detailsPanel.add(genderField);
		detailsPanel.add(balanceFieldLabel);
		detailsPanel.add(balanceField);
		//
		//depositPanel
		JPanel adBTN1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JButton Details1 = new JButton("Retrieve Account Details");
		adBTN1.add(Details1);
		JLabel acctFieldLabel1 = new JLabel("Account Number");
		JTextField accountField1 = new JTextField(5);
		JLabel nameFieldLabel21 = new JLabel("Name");
		JTextField nameField21 = new JTextField(20);
		JLabel AddressFieldLabel21 = new JLabel("Address");
		JTextField addressField21 = new JTextField(100);
		JLabel balanceFieldLabel1 = new JLabel("Balance");
		JTextField balanceField1 = new JTextField(8);
		JLabel depositFieldLabel = new JLabel("Deposit"); 
		JTextField depositField = new JTextField(8);
		depositPanel.add(acctFieldLabel1);
		depositPanel.add(accountField1);
		depositPanel.add(adBTN1);
		// action Listener for "Retrieve Account Details" button
		Details1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ConToDB D = new ConToDB();
				D.getDetails(accountField1, nameField21, addressField21, balanceField1);
				
			}
			
		});
		depositPanel.add(nameFieldLabel21);
		depositPanel.add(nameField21);
		depositPanel.add(AddressFieldLabel21);
		depositPanel.add(addressField21);
		depositPanel.add(balanceFieldLabel1);
		depositPanel.add(balanceField1);
		depositPanel.add(depositFieldLabel);
		depositPanel.add(depositField);
		JPanel BTN1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JButton Deposit = new JButton("Make Deposit");
		BTN1.add(Deposit);
		depositPanel.add(BTN1);
		//Deposit//finish adding action Listener for "Make Deposit" button
		Deposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ConToDB Dep = new ConToDB();
				Dep.makeDeposit(accountField1, depositField, balanceField1);
				
			}
			
		});
		
		

		//withdrawPanel
		JPanel adBTN11 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JButton Details11 = new JButton("Retrieve Account Details");
		adBTN11.add(Details11);
		JLabel nameFieldLabel1 = new JLabel("Enter Name");
		JTextField nameField1 = new JTextField(30);
		JLabel AddressFieldLabel1 = new JLabel("Enter Address");
		JTextField addressField1 = new JTextField(100);
		JLabel acctFieldLabel11 = new JLabel("Account Number");
		JTextField accountField11 = new JTextField(5);
		JLabel balanceFieldLabel11 = new JLabel("Balance");
		JTextField balanceField11 = new JTextField(8);
		JLabel withdrawFieldLabel = new JLabel("Withdrawal");
		JTextField withdrawField = new JTextField(8);
		withdrawPanel.add(acctFieldLabel11);
		withdrawPanel.add(accountField11);
		withdrawPanel.add(adBTN11);
		// Details11//add action Listener for withdrawal panel get "Retrieve Account Details" button
		Details11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ConToDB D = new ConToDB();
				D.getDetails(accountField11, nameField1, addressField1, balanceField11);
				
			}
			
		});
		withdrawPanel.add(nameFieldLabel1);
		withdrawPanel.add(nameField1);
		withdrawPanel.add(AddressFieldLabel1);
		withdrawPanel.add(addressField1);
		withdrawPanel.add(balanceFieldLabel11);
		withdrawPanel.add(balanceField11);
		withdrawPanel.add(withdrawFieldLabel);
		withdrawPanel.add(withdrawField);
		JPanel BTN114 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JButton Withdraw = new JButton("Make Withdrawal");
		BTN114.add(Withdraw);
		withdrawPanel.add(BTN114);
		//Withdraw// add action Listener for "Make Withdrawal" button
		Withdraw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ConToDB W = new ConToDB();
				W.makeWithdrawal(accountField11, withdrawField, balanceField11);
			}
			
		});
		
		
		//balancePanel
		JPanel adBTN111 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JButton Details111 = new JButton("Retrieve Account Details");
		adBTN111.add(Details111);
		JLabel nameFieldLabel11 = new JLabel("Enter Name");
		JTextField nameField11 = new JTextField(30);
		JLabel AddressFieldLabel11 = new JLabel("Enter Address");
		JTextField addressField11 = new JTextField(100);
		JLabel acctFieldLabel111 = new JLabel("Account Number");
		JTextField accountField111 = new JTextField(5);
		JLabel balanceFieldLabel111 = new JLabel("Balance");
		JTextField balanceField111 = new JTextField(8);
		balancePanel.add(acctFieldLabel111);
		balancePanel.add(accountField111);
		balancePanel.add(adBTN111);
		//Details111//Add action listener for "Retrieve Account Details" button for balance panel
		Details111.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ConToDB D = new ConToDB();
				D.getDetails(accountField111, nameField11, addressField11, balanceField111);
			}
			
		});
		balancePanel.add(nameFieldLabel11);
		balancePanel.add(nameField11);
		balancePanel.add(AddressFieldLabel11);
		balancePanel.add(addressField11);
		balancePanel.add(balanceFieldLabel111);
		balancePanel.add(balanceField111);
		
		
		// item listeners to set views visible
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				openPanel.setVisible(true);
				detailsPanel.setVisible(false);
				depositPanel.setVisible(false);
				withdrawPanel.setVisible(false);
				balancePanel.setVisible(false);
				
			}
		});
		
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openPanel.setVisible(false);
				detailsPanel.setVisible(true);
				depositPanel.setVisible(false);
				withdrawPanel.setVisible(false);
				balancePanel.setVisible(false);
				
			}
		});
		
		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openPanel.setVisible(false);
				detailsPanel.setVisible(false);
				depositPanel.setVisible(true);
				withdrawPanel.setVisible(false);
				balancePanel.setVisible(false);
			}
		});
		item4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openPanel.setVisible(false);
				detailsPanel.setVisible(false);
				depositPanel.setVisible(false);
				withdrawPanel.setVisible(true);
				balancePanel.setVisible(false);
			}
		});
		
		item5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openPanel.setVisible(false);
				detailsPanel.setVisible(false);
				depositPanel.setVisible(false);
				withdrawPanel.setVisible(false);
				balancePanel.setVisible(true);
			}
		});
		
		// add all basic elements
		menu1.add(item1);
		menu1.add(item2);
		menu2.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		menuBar.add(menu1);
		menuBar.add(menu2);
		win.add(menuBar, BorderLayout.NORTH);
		
		// add panels for to view
		viewPanel.add(openPanel);
		viewPanel.add(detailsPanel);
		viewPanel.add(depositPanel);
		viewPanel.add(withdrawPanel);
		viewPanel.add(balancePanel);
		
		// add view panel
		win.add(viewPanel);
		win.add(leftPanel, BorderLayout.WEST);
		win.add(rightPanel, BorderLayout.EAST);
		win.add(botPanel, BorderLayout.SOUTH);
		win.setSize(500, 1000);
		
		win.setVisible(true);
	}
}
