package Bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Balance {
	
	private String FileLoc = "C:\\Users\\rajma\\Documents\\Eclipse Java\\BankingSystem\\src\\data\\";
	
	private double bal;
	//private double fasTag = 0;
	
	private ArrayList<String> history = new ArrayList<String>();
	
	public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	public LocalDateTime now = LocalDateTime.now();  
	

	//Default Constructor
	public Balance()
	{
		bal = 0;
	}
	
	//Constructor overloading
	public Balance(double x)
	{
		bal = x;
		String s = dtf.format(now) + " --> " + x + " Amount has been deposited in your account.";
		history.add(s);
		//writeHistory(s);
	}
	
	//Method to write the current balance in customer database file
	public void writeBal(int acc)
	{
		FileWriter fw = null;
		try
		{
			
			fw =new FileWriter(FileLoc + acc + "_bal.txt");
			fw.write(String.valueOf(bal));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	
	
	//Method to read the Transaction history from the customer database file
	public void readHistory(int acc) 
	{
		BufferedReader br = null;
		FileReader fr = null;
		try
		{
			fr =new FileReader(FileLoc + acc + ".txt");
			br = new BufferedReader(fr);
			String line;
			br.readLine();
			while((line = br.readLine()) != null)
			{
				history.add(line);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
	}
	
	//Method to write transaction history into customer databse file
	public void writeHistory(String s, int acc)
	{
		FileWriter fw = null;
		
		try
		{
			fw =new FileWriter(FileLoc + acc + ".txt", true);
			fw.write(s + "\n");
			fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	//getter method for balance
	public double getBal()
	{
		return bal;
	}
	//Setter method for balance
	public void setBal(double x)
	{
		bal = x;
	}
	
	
	//method to deposit money 
	public String Deposit(double x)
	{
		bal += x;
		
		
		System.out.println(x + "  Amount Deposited!\n");
		
		//Adding transaction history
		String s =  dtf.format(now) + " --> " + x + " Amount has been deposited in your account.";
		history.add(s);
		
		//System.out.println("****************************************************************************************************************************");
		
		return s;
	}

	//Method to display transaction history
	public void showHistory()
	{
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------\n");
		System.out.println("Your transaction history is as follows : \n");
		for(String s : history)
		{
			System.out.println(s);
		}
		System.out.println("\n\nYour final balance is : " + bal);
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------\n");
	}
	
	
	//Method to withdraw money from account
	public String withdraw(double x)
	{
		if(x > bal)
		{
			//if account does not have sufficient balance then return empty string
			System.out.println("\nError: Sorry you cannot withdraw an amount larger than your balance!!!\n");
			return "";
		}
		else
		{
			bal -= x;
			
			//System.out.println("----------------------------------------------------------------------------------------------------------------------------");
			//System.out.println("----------------------------------------------------------------------------------------------------------------------------\n");
			
			System.out.print("Enter a note for tracking your expenditures : ");
			Scanner sc = new Scanner(System.in);
			
			String s = sc.next();
		    System.out.print("\n");


			//notify
			System.out.println(x + " Amount has been deducted from your account at " + dtf.format(now) );
			
			//Adding to transaction history
			String ss = dtf.format(now) + " --> " + x + " Amount deducted for : " + s;
			history.add(ss);
			
			//System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
			//System.out.println("----------------------------------------------------------------------------------------------------------------------------");
			
			//returning transaction string
			return ss;
		}
	}
	
	
	//Method to transfer money from this account to another
	public String transfer(Customer c2, double x)
	{
		bal -= x;
		String s1 =  dtf.format(now) + " --> " + x + " Amount transfered to Account Number : " + c2.getAccNo();
		history.add(s1);
		
		return s1;
	}
	
	//Method to accept the the transfer money
	public String acceptTransfer(int accountNo, double x)
	{
		bal += x;
		String s2 = dtf.format(now) + " --> " + x + " Amount received from Account Number : " + accountNo;
		history.add(s2);
		
		return s2;
	}

	//Method to apply for cheque book
	public String chequeBook(double x)
	{
		if(x > bal)
		{
			//if account does not have sufficient balance then return empty string
			System.out.println("\nError: Sorry insufficeint balance!!!");
			return "";
		}
		else
		{
			bal -= x;
			String s = dtf.format(now) + " --> " + x + " Amount deducted for cheque book.";
			history.add(s);
			return s;
		}	
	}
	
	//method to recharge your fastag balance
	public String rechargeFastag(double x)
	{
		if(x > bal)
		{
			//if account does not have sufficient balance then return empty string
			System.out.println("\nError: Sorry insufficeint balance!!!");
			return "";
		}
		else
		{
			bal -= x;

			String s = dtf.format(now) + " --> " + x + " Amount deducted for recharge of fastag.";
			history.add(s);
			return s;
		}
		
	}
	
	

	/*
	public static void main(String args[])
	{
		Balance b1 = new Balance(500.00);
		
		b1.Deposit(1100);
		b1.Deposit(800);
		
		b1.withdraw(200);
		b1.withdraw(501);
		
		
		
		b1.showHistory();
	}
	*/
	

}
