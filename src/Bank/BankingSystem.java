package Bank;

import java.util.HashMap;
import java.util.Scanner;

public class BankingSystem extends Customer
{
	
	private HashMap<Integer,Customer> MAP = new HashMap<Integer,Customer>();
	

	//ArrayList<Customer> TC = new ArrayList<Customer>(); //total customer
	Scanner sc = new Scanner(System.in);
	
	public void Start()
	{
		boolean flag = true;
		while(flag)
		{
			 System.out.println("-----------------------------------------MAIN MENU----------------------------------------- ");
		     System.out.println("1) CREATE NEW ACCOUNT");
		     System.out.println("2) LOGIN");
		     System.out.println("3) EXIT\n");
		     
			System.out.print("Enter Your choice :  ");
			int choice = sc.nextInt();
			
			System.out.println("\n");
			
			switch(choice)
			{
				case 1 : 
					createAccount();
					break;
				case 2 :
					Login();
					break;
				case 3 :
					flag = false;
					break;
				default :
					System.out.println("Wrong choice!!!");
					break;
			}
				
		}
	}
	
	
	public void Login()
	{
		System.out.print("Enter your Account Numner : ");
		int acc = sc.nextInt();
		
		
		if(MAP.containsKey(acc))
		{
			Customer c = MAP.get(acc);
				
			System.out.print("Enter your Password : ");
			String pass = sc.next();
			pass = pass.trim();
		
			//System.out.println("\n\n rajs pass :=====" + pass + "===\n\n");
			//System.out.println("\n\n sysy pass acc :=====" + c.getPass() + "===\n\n");
			//System.out.println("\n\n system name :=====" + c.getName() + "===\n\n");
			
			if( (c.getPass() ).equals(pass))
			{
				//boolean ff = true;
				
				
				
							//enter another switch while loop
							
				
							boolean l = true;
							while(l) {
								
								//
								
								System.out.println("1) SHOW INFO");
								System.out.println("2) DEPOSIT MONEY");
								System.out.println("3) WITHDRAW MONEY");
								System.out.println("4) SHOW TRANSACTION HISTORY");
								System.out.println("5) TRANSFER MONEY");
								System.out.println("6) EXIT\n");
								
								System.out.print("ENTER YOUR CHOICE : ");
								int cc = sc.nextInt();
								System.out.println("\n");
								
								//
								
								switch(cc){
			
						        case 1:
						        	//info
						        	c.showInfo();
						        	break;
			
						        case 2:
						        	//deposit
						        	c.Deposit();
						        	break;
			
						        case 3:
						        	//withdraw
						        	c.withdraw();
						        	break;
			
						        case 4:
						        	//history
						        	c.showHistory();
						        	break;
						        
						        case 6:
						        	//exit
						        	l = false;
						        	break;
						        	
						        case 5:
						        	
						        	System.out.print("Enter Account Number of the transferee : "); 
						        	int a2 = sc.nextInt();
						        	System.out.print("Enter Account to be transfered : "); 
						        	int x = sc.nextInt();
						        	
						        	double bb = c.getBalance();
						        	if(x > bb)	
						        	{
							        	System.out.println("Insufficient funds!!!"); 
						        	}
						        	else
						        	{
						        		if(MAP.containsKey(a2))
						        		{
						        			Customer c2 = MAP.get(a2);
						        			c.transfer(c2, x);
						      
						        		}
						        		else
						        		{
						        			System.out.println("No account with this number found");
						        		}
						        		
						        	}
						        	
						        	
						        	break;
						        
						        default:
						        	System.out.println("ERROR : please check your input!!!\n");
						        	break;
						        	
								}//switch
								
							}//while
								

			}
			else
			{
				System.out.println("Password invalid!!!!");
			}
		}
		else
		{
			System.out.println("No account number found!!!");
		}
	
	}
	
	public void createAccount()
	{
		Customer c = new Customer();
		c.addCustomer();
		MAP.put(c.getAccNo(), c);
		
		c.showInfo();
		
		//TC.add(c);
	}
	
	
	public static void main(String args[])
	{
		BankingSystem BB = new BankingSystem();
		BB.Start();
	}
	
	
	
}//class
