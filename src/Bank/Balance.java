package Bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Balance {
	
	private String FileLoc = "C:\\Users\\rajma\\Documents\\Eclipse Java\\BankingSystem\\src\\data\\";
	
	private double bal;
	private double fasTag = 0;
	
	private ArrayList<String> history = new ArrayList<String>();
	
	public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	public LocalDateTime now = LocalDateTime.now();  
	
	//private Customer c;
	
	
	//constructor overloading
	
	public void writeBal(int acc)
	{
		try
		{
			
			FileWriter fw =new FileWriter(FileLoc + acc + "_bal.txt");
			fw.write(String.valueOf(bal));
			fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void readHistory(int acc)
	{
		try
		{
			FileReader fr =new FileReader(FileLoc + acc + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			br.readLine();
			while((line = br.readLine()) != null)
			{
				history.add(line);
			}
			fr.close();
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void writeHistory(String s, int acc)
	{
		try
		{
			FileWriter fw =new FileWriter(FileLoc + acc + ".txt", true);
			fw.write(s + "\n");
			fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Balance()
	{
		bal = 0;
	}
	

	public Balance(double x)
	{
		bal = x;
		String s = dtf.format(now) + " --> " + x + " Amount has been deposited in your account.";
		history.add(s);
		//writeHistory(s);
	}
	
	public double getBal()
	{
		return bal;
	}
	public void setBal(double x)
	{
		bal = x;
	}
	
	public String Deposit(double x)
	{
		bal += x;
		System.out.println(x + "  Amount Deposited!\n");
		
		String s =  dtf.format(now) + " --> " + x + " Amount has been deposited in your account.";
		history.add(s);
		
		return s;
		//writeHistory(s);
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
	
	
	public String withdraw(double x)
	{
		if(x > bal)
		{
			System.out.println("Error: Sorry you cannot withdraw an amount larger than your balance!!!");
			return "";
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
			String ss = dtf.format(now) + " --> " + x + " Amount deducted for : " + s;
			history.add(ss);
			//writeHistory(dtf.format(now) + " --> " + x + " Amount deducted for : " + s);
			return ss;
		}
	}
	
	
	public String transfer(Customer c2, double x)
	{
		bal -= x;
		String s1 =  dtf.format(now) + " --> " + x + " Amount transfered to Account Number : " + c2.getAccNo();
		history.add(s1);
		
		return s1;
		//writeHistory(s1);
		//c2.acceptTransfer(this,x);
	}
	
	public String acceptTransfer(int accountNo, double x)
	{
		bal += x;
		String s2 = dtf.format(now) + " --> " + x + " Amount received from Account Number : " + accountNo;
		history.add(s2);
		
		return s2;
		//writeHistory(s2);
	}
	
	public String chequeBook(double x)
	{
		bal -= x;
		String s = dtf.format(now) + " --> " + x + " Amount deducted for cheque book.";
		history.add(s);
		
		return s;
	}
	public String rechargeFastag(double x)
	{
		bal -= x;
		String s = dtf.format(now) + " --> " + x + " Amount deducted for recharge of fastag.";
		history.add(s);
		return s;
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
