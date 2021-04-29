package Bank;

import java.util.*;

public class Customer extends Balance implements Runnable
{
	
	private String name, gender, MNo, pass;
	
	private static int ID = 0;
	
	private int age, AccNo, AccType;
	
	Balance b = new Balance();
	
	Scanner sc = new Scanner(System.in);
	
	//for multi-threading
	private Customer C2;
	private double Tmoney;
	private int accountNo;
	
	
	//default constructor
	public Customer()
	{
		ID++;
		age = 0;
		AccNo = 0;
		AccType = 0;	
	}
	
	
	//Constructor overloading for taking parameters so that theses parameters can be used by the thread
	public Customer(Customer cc, double xx, int acc)
	{
		C2  = cc;
		Tmoney = xx;
		accountNo = acc;
	}


	//AccNo, name , age, gender , mobile, AccType, password
	//constructor overloading
	public Customer(int accno, int acctype, String name, int age, String gender, String mobile, String password)
	{
		ID++;
		AccNo = accno;
		AccType = acctype;
		this.name = name;
		this.age = age;
		this.gender = gender;
		MNo = mobile;
		pass = password;
	}
	
	//Input customer details
	public void addCustomer()
	{
		AccNo = ID;		
		/*
		 * System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------\n");
		 */
		
		System.out.println("\n************************************************************************************************************************************************\n");
		System.out.print("Which type of account you want to open ? \n1) Savings Account \n2) Current Account\n");
		boolean done = false;
        while(done == false)
        {
            try 
            {
            	AccType = sc.nextInt();
            	if(AccType == 1 || AccType == 2)
            	{
            		done = true;
            		break;
            	}
            	else
            	{
            		Exception e = new Exception ();
            		throw e;
            	}
            	
            }
            catch(Exception e)
            {
            	System.out.println("\nAccount Type can either be 1 or 2 ; Please recheck your answer!!!\n");
            	System.out.print("Which type of account you want to open ? \n1) Savings Account \n2) Current Account\n");
            	sc.nextLine(); //clearing buffer
            }
        }
        
		
		
		System.out.print("Enter Name : ");
		name = sc.next();
		System.out.print("\n");
		
		System.out.print("Enter Age : ");
		age = sc.nextInt();
		System.out.print("\n");
		
		System.out.print("Enter gender : ");
		gender = sc.next();
		System.out.print("\n");
		//sc.next();
		
		
		System.out.print("Enter Mobile Number : ");
		MNo = sc.next();
		//sc.next();
		System.out.print("\n");
		
		/*
		System.out.print("Which type of account you want to open ? \n 1) Savings Account \n 2) Current Account\n");
		AccType = sc.nextInt();
		sc.next();
		System.out.print("\n");
		*/
		
		System.out.print("Enter Password for your account : ");
		pass = sc.next();
		pass = pass.trim();
		//sc.next();
		System.out.print("\n\n\n");

		
		
		System.out.print("*****  Your Account ID is : " + AccNo + "  *****\n\n");
		
		
		//System.out.println("************************************************************************************************************************************************\n");
		
	}
	
	/*
	 * getter methods
	 */
	
	//get AccType
	public int getAccType()
	{
		return AccType;
	}
	//getName
	public String getName()
	{
		return name;
	}
	//getGender
	public String getGender()
	{
		return gender;
	}
	//getMNo
	public String getMno()
	{
		return MNo;
	}
	//getAge
	public int getAge()
	{
		return age;
	}
	//getAccNo
	public int getAccNo()
	{
		return AccNo;
	}
	//getBalance
	public double getBalance()
	{
		return b.getBal();
	}	
	//getTotalAccounts in bank
	public static int getTotalAcc()
	{
		return ID;
	}	
	//getPassword
	public String getPass()
	{
		return pass;
	}

	
	//Display Customer Info
	public void showInfo()
    {
		System.out.println("\n************************************************************************************************************************************************\n");
		
		System.out.println("\nNAME : "+ name);
	    System.out.println("GENDER : "+ gender);
	    System.out.println("MOBILE NO. : "+MNo);
	    System.out.println("ACCOUNT NO. : "+ AccNo);
	    if(AccType == 1)
	    {
	    	 System.out.println("TYPE OF ACCOUNT : Savings Account");
	    }
	    else if(AccType == 2)
	    {
	    	 System.out.println("TYPE OF ACCOUNT : Current Account");
	    }
	    
	    System.out.println("AGE : "+ age );
	   
	    System.out.println("\n************************************************************************************************************************************************\n");
	    
    }
	

	//Method to deposit money in the bank
	public void Deposit()
	{
		//Inputing amount to be deposited
		System.out.println("\n************************************************************************************************************************************************\n");
		System.out.print("Enter Amount to be deposited : ");
		double m = sc.nextInt(); 
		
		//creating transaction history string
		String s = b.Deposit(m);
		
		//writing the transaction in customer database
		b.writeHistory(s, AccNo);
		
		//Updating Balance in customer database
		b.writeBal(AccNo);
		System.out.println("\n************************************************************************************************************************************************\n");
	}
	
	//Method to withdraw money from your account
	public void withdraw()
	{
		//Inputing amount to withdraw 
		System.out.println("\n************************************************************************************************************************************************\n");
		System.out.print("Enter Amount to be withdrawed : ");
		double m = sc.nextInt(); 
		
		//creating transaction history String
		String s = b.withdraw(m);
		
		if(!s.equals("") && s != null)
		{
			//writing the transaction in customer database
			b.writeHistory(s, AccNo);
			
			//Updating Balance in customer database
			b.writeBal(AccNo);	
		}
		System.out.println("\n************************************************************************************************************************************************\n");
	}
	
	
	//method to show transaction history
	public void showHistory()
	{
		b.showHistory();
	}
	
	//Method to transfer money from your accounnt to another
	public void Transfer(Customer c2, double x)
	{
		/*
		 * Starting a thread so that both the process :
		 * 		-- Deduction of money from your account
		 * 		-- Addition of money to the transferee account
		 * Both the process occurs simultaneously
		 */
		Thread th = new Thread(new Customer(c2,x,AccNo));
		th.start();
		
		//Writing the transaction in the account database
		String s = b.transfer(c2,x);
		b.writeHistory(s, AccNo);
		
		//Updating the balance of the account in the database
		b.writeBal(AccNo);
	}	
	
	//Thread run() method
	public void run()
	{
		//System.out.println("\n\n ..........Thread.........\n\n");
		//Calling the method to Accept the money transfer
		C2.AcceptTransfer(accountNo, Tmoney);
	}
	
	//Method to Accept the money transfer
	public void AcceptTransfer(int accountNo, double x)//run
	{
		//writing the transaction in customer database
		String s = b.acceptTransfer(accountNo, x);
		b.writeHistory(s, AccNo);
		
		//Updating Balance in customer database
		b.writeBal(AccNo);
	}

	//Method to read the transaction history from Customer database file
	public void ReadHistory()
	{
		b.readHistory(AccNo);
	}
	
	//Setter method to set the balance to a specific amount 'x'
	public void setBal(double x)
	{
		b.setBal(x);
	}
	
	//Method to apply for ChequeBook
	public void ChequeBook(double x)
	{
		String s = b.chequeBook(x);
		b.writeHistory(s, AccNo);
		b.writeBal(AccNo);
	}
	
	//Method to recharge Fastag balance
	public void RechargeFastag(double x)
	{
		String s = b.rechargeFastag(x);
		b.writeHistory(s, AccNo);
		b.writeBal(AccNo);
	}
	
	
	/*
	public static void main(String arg[])
	{
		System.out.println("frfffff");
		
		Customer c = new Customer();
		c.addCustomer();
		Customer c1 = new Customer();
		c1.addCustomer();
		
		System.out.println("\n\n\n" + c.getName());

	}
	*/
	

}
