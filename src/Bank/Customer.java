package Bank;

import java.util.*;

public class Customer extends Balance implements Runnable
{
	
	private String name, gender, MNo, pass;
	
	private static int ID = 0;
	
	private int age, AccNo, AccType;
	
	Balance b = new Balance();
	
	Scanner sc = new Scanner(System.in);
	
	//for multithreading
	private Customer C2;
	private double Tmoney;
	private int accountNo;


	//AccNo, name , age, gender , mobile, AccType, password
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
	
	public Customer()
	{
		ID++;
		age = 0;
		AccNo = 0;
		AccType = 0;	
	}
	
	public void addCustomer()
	{
		AccNo = ID;		
		
		System.out.print("Which type of account you want to open ? \n 1) Savings Account \n 2) Current Account\n");
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
            	System.out.println("Account Type can either be 1 or 2 ; Please recheck your answer!!!");
            	System.out.print("Which type of account you want to open ? \n 1) Savings Account \n 2) Current Account\n");
            	sc.nextLine(); //clearing buffer
            }
        }
        
		
		
		System.out.println("Enter Name : ");
		name = sc.next();
		System.out.print("\n");
		
		System.out.print("Enter Age : ");
		age = sc.nextInt();
		System.out.print("\n");
		
		System.out.println("Enter gender : ");
		gender = sc.next();
		//sc.next();
		
		System.out.print("\n");
		
		System.out.println("Enter Mobile Number : ");
		MNo = sc.next();
		//sc.next();
		System.out.print("\n");
		
		/*
		System.out.print("Which type of account you want to open ? \n 1) Savings Account \n 2) Current Account\n");
		AccType = sc.nextInt();
		sc.next();
		System.out.print("\n");
		*/
		
		System.out.println("Enter Password for your account : ");
		pass = sc.next();
		pass = pass.trim();
		//sc.next();
		System.out.print("\n\n\n");

		
		
		System.out.print("Your Account ID is : " + AccNo + "\n\n");
	}
	
	public int getAccType()
	{
		return AccType;
	}
	
	public String getName()
	{
		return name;
	}
	public String getGender()
	{
		return gender;
	}
	public String getMno()
	{
		return MNo;
	}
	public int getAge()
	{
		return age;
	}
	public int getAccNo()
	{
		return AccNo;
	}
	public double getBalance()
	{
		return b.getBal();
	}	
	public static int getTotalAcc()
	{
		return ID;
	}	
	public String getPass()
	{
		return pass;
	}

	public void showInfo()
    {
		
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
	   
	    System.out.println("\n");
	    
    }
	
	//
	
	//public writeHistory
	
	public void Deposit()
	{
		System.out.print("Enter Amount to be deposited : ");
		double m = sc.nextInt(); 
		//String s =  dtf.format(now) + " --> " + m + " Amount has been deposited in your account.";
		//b.writeHistory(s, AccNo);
		String s = b.Deposit(m);
		b.writeHistory(s, AccNo);
		b.writeBal(AccNo);
	}
	
	public void withdraw()
	{
		System.out.print("Enter Amount to be withdrawed : ");
		double m = sc.nextInt(); 
		
		String s = b.withdraw(m);
		if(!s.equals("") && s != null)
		{
			b.writeHistory(s, AccNo);
			b.writeBal(AccNo);
			
		}
	}
	
	public void showHistory()
	{
		b.showHistory();
	}
	
	public void Transfer(Customer c2, double x)
	{
		//run() 
		Thread th = new Thread(new Customer(c2,x,AccNo));
		th.start();
		String s = b.transfer(c2,x);
		System.out.println("\n\n writing history from transfer in cus\n\n");
		b.writeHistory(s, AccNo);
		b.writeBal(AccNo);
		
		
		//c2.acceptTransfer(this, x);
	}	
	public Customer(Customer cc, double xx, int acc)
	{
		C2  = cc;
		Tmoney = xx;
		accountNo = acc;
	}
	public void run()
	{
		System.out.println("\n\n ..........Thread.........\n\n");
		C2.AcceptTransfer(accountNo, Tmoney);
	}
	
	public void AcceptTransfer(int accountNo, double x)//run
	{
		String s = b.acceptTransfer(accountNo, x);
		System.out.println("\n\n return s2 : " + s + "\n\n");
		b.writeHistory(s, AccNo);
		b.writeBal(AccNo);
	}
	
	public void ReadHistory()
	{
		b.readHistory(AccNo);
	}
	
	public void setBal(double x)
	{
		b.setBal(x);
	}
	
	public static void main(String arg[])
	{
		System.out.println("frfffff");
		
		Customer c = new Customer();
		c.addCustomer();
		Customer c1 = new Customer();
		c1.addCustomer();
		
		System.out.println("\n\n\n" + c.getName());

	}
	

}
