package Bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Balance {
	
	private double bal;
	private double fasTag = 0;
	
	private ArrayList<String> history = new ArrayList<String>();
	
	public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	public LocalDateTime now = LocalDateTime.now();  
	
	
	
	
	//constructor overloading
	
	public Balance()
	{
		bal = 0;
	}
	public Balance(double x)
	{
		bal = x;
		String s = dtf.format(now) + " --> " + x + " Amount has been deposited in your account.";
		history.add(s);
	}
	
	public double getBal()
	{
		return bal;
	}
	
	public void Deposit(double x)
	{
		bal += x;
		System.out.println(x + "  Amount Deposited!\n");
		
		String s =  dtf.format(now) + " --> " + x + " Amount has been deposited in your account.";
		history.add(s);
	}
	
	public void showHistory()
	{
		System.out.println("\n\n----------------------------------------------------------------------------------------------------------------");
		System.out.println("Your transaction history is as follows : \n");
		for(String s : history)
		{
			System.out.println(s);
		}
		System.out.println("\n\nYour final balance is : " + bal);
		System.out.println("----------------------------------------------------------------------------------------------------------------\n");
	}
	
	
	public void withdraw(double x)
	{
		if(x > bal)
		{
			System.out.println("Error: Sorry you cannot withdraw an amount larger than your balance!!!");
		}
		else
		{
			bal -= x;
						
			System.out.print("Enter a note for tracking your expenditures : \n");
			
			//String s = "";
			Scanner sc = new Scanner(System.in);
			
			String s = sc.next();
		    System.out.print("\n");


			//notify
			System.out.println(x + " Amount has been deducted from your account at " + dtf.format(now) );
			
			//history
			history.add(dtf.format(now) + " --> " + x + " Amount deducted for : " + s);
			
		}
	}
	
	public void transfer(Customer c2, int x)
	{
		bal -= x;
		String s1 =  dtf.format(now) + " --> " + x + " Amount transfered to Account Number : " + c2.getAccNo();
		history.add(s1);
		//c2.acceptTransfer(this,x);
	}
	
	public void acceptTransfer(Customer c1, int x)
	{
		String s2 = dtf.format(now) + " --> " + x + " Amount received from Account Number : " + c1.getAccNo();
		history.add(s2);
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
