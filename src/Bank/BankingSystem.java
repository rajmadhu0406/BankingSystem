package Bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

//import jdk.vm.ci.code.site.ExceptionHandler;

public class BankingSystem extends Customer
{

	
	private HashMap<Integer,Customer> MAP = new HashMap<Integer,Customer>();
	private String FileLoc = "C:\\Users\\rajma\\Documents\\Eclipse Java\\BankingSystem\\src\\data\\";
	private LoanCalculator LC = new LoanCalculator();
	

	//ArrayList<Customer> TC = new ArrayList<Customer>(); //total customer
	Scanner sc = new Scanner(System.in);
	
	public void Start()
	{
		boolean flag = true;
		while(flag)
		{
			
			 System.out.println("---------------------------------------------------------------------------------MAIN MENU------------------------------------------------------------------------------------------------ ");
		     System.out.println("\n(1) CREATE NEW ACCOUNT\n");
		     System.out.println("(2) LOGIN\n");
		     System.out.println("(3) EXIT\n");
		
			System.out.print("Enter Your choice :  ");
			
			int	choice = sc.nextInt();
			
			
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
					System.out.println("ERROR Please choose from above options only!");
					break;
			}
				
		}
	}
	
	
	public void Login()
	{ 
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------\n");
		
		System.out.print("Enter your Account Numner : ");
		
		int acc = 0;
		try {
			acc = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Error!!!");
			e.printStackTrace();
		}
		
		
		if(MAP.containsKey(acc))
		{
			Customer c = MAP.get(acc);
				
			System.out.print("Enter your Password : ");
			String pass = sc.next();
			pass = pass.trim();
		
			//System.out.println("\n\n rajs pass :=====" + pass + "===\n\n");
			//System.out.println("\n\n sysy pass acc :=====" + c.getPass() + "===\n\n");
			//System.out.println("\n\n system name :=====" + c.getName() + "===\n\n");
			
			System.out.println("----------------------------------------------------------------------------------------------------------------------------");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------");
			
			if( (c.getPass() ).equals(pass))
			{

							boolean l = true;
							while(l) {
								
								//
								
								System.out.println("\n1) ==>  SHOW INFO");
								System.out.println("\n2) ==>  DEPOSIT MONEY");
								System.out.println("\n3) ==>  WITHDRAW MONEY");
								System.out.println("\n4) ==>  SHOW TRANSACTION HISTORY");
								System.out.println("\n5) ==>  TRANSFER MONEY");
								System.out.println("\n6) ==>  APPLY FOR CHEQUE BOOK");
								System.out.println("\n7) ==>  RECHARGE FASTAG");
								System.out.println("\n8) ==>  LOAN CALCULATOR");
								System.out.println("\n9) ==>  EXIT\n");
								
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
						        
						        case 5:
						    		System.out.println("************************************************************************************************************************************************\n");
						        	System.out.print("Enter Account Number of the transferee :  "); 
						        	int a2 = sc.nextInt();
						        	System.out.print("\nEnter Amount to be transfered : "); 
						        	int x = sc.nextInt();
						        	System.out.println();
						        	double bb = c.getBalance();
						        	if(x > bb)	
						        	{
							        	System.out.println("Insufficient funds!!!\n"); 
						        	}
						        	else
						        	{
						        		if(MAP.containsKey(a2))
						        		{
						        			Customer c2 = MAP.get(a2);
						        			c.Transfer(c2, x);
						      
						        		}
						        		else
						        		{
						        			System.out.println("No account with this number found\n");
						        		}
						        		
						        	}
						        	
						    		System.out.println("\n************************************************************************************************************************************************\n");
						        	break;
						        
						        case 6:
						    		System.out.println("************************************************************************************************************************************************\n");
						        	System.out.print("Enter Cheque Book price : ");
						        	double cb = sc.nextDouble();
						        	c.ChequeBook(cb);
						    		System.out.println("\n************************************************************************************************************************************************\n");
						        	break;
						        	
						        case 7:
						        	//to do
						    		System.out.println("************************************************************************************************************************************************\n");
						        	System.out.print("Enter Recharge amount : ");
						        	double r = sc.nextDouble();
						        	c.RechargeFastag(r);
						        	System.out.println();
						    		System.out.println("\n************************************************************************************************************************************************\n");
						        	break;
						        	
						        case 8:
						        	LC.calc();
						        	break;
						        	
						        case 9:
						        	//exit
						        	l = false;
						        	break;
						        	
						        default:
						        	System.out.println("\nERROR : please choose from above options only!!!\n");
						        	break;
						        	
								}//switch
								
							}//while
								

			}
			else
			{
				System.out.println("\nPassword invalid!!!!");
			}
		}
		else
		{
			System.out.println("\nNo account number found!!!");
		}
	
	}
	
	public void createAccount() 
	{
		Customer c = new Customer();
		c.addCustomer();
		FileWriter fw = null;
		FileWriter bfw = null;
		
		
		
		try {
				File file = new File(FileLoc + c.getAccNo() + ".txt");
				File fbal = new File(FileLoc + c.getAccNo() + "_bal.txt");
				boolean flag = file.createNewFile();	
				boolean Bflag = fbal.createNewFile();
				if(flag == false || Bflag == false)
				{
					System.out.println("flag false, file not created");
				}
				fw = new FileWriter(FileLoc + c.getAccNo() + ".txt",true);
				String cus = c.getAccNo() + "," + c.getAccType() + "," + c.getName().trim() + "," + c.getAge() + "," + c.getGender().trim() + "," + c.getMno().trim() + "," + c.getPass().trim() + "\n"; 
				fw.write(cus);
				
				bfw = new FileWriter(FileLoc + c.getAccNo() + "_bal.txt");
				bfw.write("0.0");
				bfw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(fw != null)
			{
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		
		MAP.put(c.getAccNo(), c);
		//c.showInfo();
		//TC.add(c);
	}
	
	public void LoadCustomer() 
	{
		 
		Customer c = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		FileReader Bfr = null;
		BufferedReader Bbr = null;
		
		
		try {
			 File dir = new File(FileLoc);
			 String[] fileNames = dir.list();
			 
			 
		    String line;  
		   
		    for (String fileName : fileNames) {
		    	if(!fileName.contains("bal"))
		    	{
			    	fr = new FileReader(FileLoc + fileName);
					br = new BufferedReader(fr);
					
					    line = br.readLine();
				    
				    	String[] ss = line.split(",");
				    	
				    	int accno = (int)Integer.parseInt(ss[0].trim());
				    	int acctype = (int)Integer.parseInt(ss[1].trim());
				    	String name = ss[2].trim();
				    	int age = (int)Integer.parseInt(ss[3].trim());
				    	String gender = ss[4].trim();
				    	String mobile = ss[5].trim();
				    	String password = ss[6].trim();
				    	
				    	c = new Customer(accno, acctype, name, age, gender, mobile, password);
				    	MAP.put(accno, c);
				    	
				    	c.ReadHistory();  
		    	}
		    	else
		    	{
		    		//System.out.println("\n\n file name in bfw : " + fileName + "\n");
			    	Bfr = new FileReader(FileLoc + fileName);
					Bbr = new BufferedReader(Bfr);
					String balance = Bbr.readLine();
					//balance.trim();
					double b = Double.parseDouble(balance);
					c.setBal(b);
					
					Bfr.close();
					Bbr.close();
		    	}
		    }
		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		
	}
	
	
	public static void main(String args[])
	{
		
		
		BankingSystem BB = new BankingSystem();
		//System.out.println("*****************************************************************************************************************************************************************************************************************************************");
		System.out.println("******************************************************************************* CJP PROJECT ********************************************************************************************************************************************\n");
		//System.out.println("*****************************************************************************************************************************************************************************************************************************************\n");
							
		BB.LoadCustomer();
		BB.Start();
	}
	
	
	
}//class
