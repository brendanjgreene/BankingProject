import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConToDB {
	Connection connect = null;
	Statement statement = null;
	ResultSet resultSet = null;
	String url = "jdbc:mysql://localhost:3306/banking";
	String user="root";
	String password="";
	
	public void createAccount(String n, String a) {
		String type = P.t;
		String gender = P.g;
		String retName, retAcct;
		try {
			connect = DriverManager.getConnection(url, user, password);
			statement = connect.createStatement();
			statement.executeUpdate("INSERT INTO accounts VALUES((SELECT concat('"+type+"','"+gender+"', IFNULL(LPAD(MAX(substr(acctno, 3, 3))+1, 3, '0'), '001')) FROM (select * from accounts) as p WHERE substr(acctno, 1, 1) ='"+type+"'),'"+ n +"', '"+a+"')");
			
			resultSet=statement.executeQuery("Select name, acctno from accounts where acctno=(SELECT concat('"+type+"','"+gender+"', MAX(substr(acctno, 3, 3))) FROM (select * from accounts) as p WHERE substr(acctno, 1, 1) ='"+type+"')");
			
			while (resultSet.next()) {
				retName = resultSet.getString(1);
				retAcct = resultSet.getString(2);
				System.out.println(retName+" "+retAcct);
				JFrame M = new JFrame();
				JPanel messagePanel = new JPanel();
				JLabel MessageLabel = new JLabel("  Account Name: "+retName+" Account Number: "+retAcct+ "  ");
				JButton okbutton = new JButton("OK");
				messagePanel.add(MessageLabel);
				messagePanel.add(okbutton);
				okbutton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						M.dispose();
					}
					
				});
				M.add(messagePanel);
				M.setForeground(Color.YELLOW);
				M.setSize(400, 100);
				M.setVisible(true);
			}
			connect.close();
		}catch (Exception e1){
			System.out.println(e1);
		}
		
	}


	public void getDetails(JTextField accountField, JTextField nameField, JTextField addressField,
			JTextField balanceField) {
		// TODO Auto-generated method stub
		String a = accountField.getText();
		try {
			connect = DriverManager.getConnection(url, user, password);
			statement = connect.createStatement();
			resultSet=statement.executeQuery("SELECT name, address, (SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE ACCTNO ='"+a+"') as balance from accounts where acctno ='"+a+"'");
			if (resultSet.next()) {
				String retName = resultSet.getString(1);
				String retaddress = resultSet.getString(2);
				String retbalance = resultSet.getString(3);
				nameField.setText(retName);
				addressField.setText(retaddress);
				balanceField.setText(retbalance);
			}else {
				nameField.setText("");
				addressField.setText("");
				balanceField.setText("");
				JFrame M = new JFrame();
				JPanel messagePanel = new JPanel();
				JLabel MessageLabel = new JLabel("Account Number: "+a+ " doesn't exist");
				JButton okbutton = new JButton("OK");
				messagePanel.add(MessageLabel);
				messagePanel.add(okbutton);
				okbutton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						M.dispose();
					}
					
				});
				M.add(messagePanel);
				M.setForeground(Color.YELLOW);
				M.setSize(400, 100);
				M.setVisible(true);
			}
			connect.close();
		}catch(Exception e1) {
			System.out.println(e1);
			
		}
		
	}

	public void makeDeposit(JTextField accountField1, JTextField depositField, JTextField balanceField1) {
		// TODO Auto-generated method stub
		try {
			connect = DriverManager.getConnection(url, user, password);
			statement = connect.createStatement();
			statement.executeUpdate("INSERT INTO transactions VALUES('D','"+accountField1.getText()+"','"+depositField.getText()+"',(SELECT CURRENT_TIMESTAMP()) )");
			resultSet=statement.executeQuery("SELECT (SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE ACCTNO='"+accountField1.getText()+"') as balance from accounts where acctno='"+accountField1.getText()+"'");
			if (resultSet.next()) {
				balanceField1.setText(resultSet.getString(1));
				depositField.setText("");
			}
			connect.close();
		}catch(Exception e1) {
			System.out.println(e1);
			
		}
	}

	public void makeWithdrawal(JTextField accountField11, JTextField withdrawField, JTextField balanceField11) {
		// TODO Auto-generated method stub
		try {
			connect = DriverManager.getConnection(url, user, password);
			statement = connect.createStatement();
			float balance = Float.parseFloat(balanceField11.getText());
			float withdrawal = Float.parseFloat(withdrawField.getText());
			if (withdrawal<=balance) {
				statement.executeUpdate("INSERT INTO transactions VALUES('W','"+accountField11.getText()+"','-"+withdrawField.getText()+"',(SELECT CURRENT_TIMESTAMP()) )");
				resultSet=statement.executeQuery("SELECT (SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE ACCTNO='"+accountField11.getText()+"') as balance from accounts where acctno='"+accountField11.getText()+"'");
				if (resultSet.next()) {
					balanceField11.setText(resultSet.getString(1));
					withdrawField.setText("");
				}
			}else {
				JFrame M = new JFrame();
				JPanel messagePanel = new JPanel();
				JLabel MessageLabel = new JLabel("You have inssufficient funds");
				JButton okbutton = new JButton("OK");
				messagePanel.add(MessageLabel);
				messagePanel.add(okbutton);
				okbutton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						M.dispose();
					}
					
				});
				M.add(messagePanel);
				M.setForeground(Color.YELLOW);
				M.setSize(400, 100);
				M.setVisible(true);
			}
			
			connect.close();
		}catch(Exception e1) {
			System.out.println(e1);
			
		}
	}

}

