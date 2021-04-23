 package Bank;

import java.io.*;
import java.util.ArrayList;

public interface filetestt {
	
	public static void mmain(String args[])
	{
		try {
			FileWriter fw=new FileWriter("C:\\Users\\rajma\\Documents\\Eclipse Java\\BankingSystem\\src\\data\\pass.txt",true);
			String a = "raj";
			String b = "123";
			//fw.write("raj,19,male,pass1\n");
			fw.write(a+","+b+","+"\n");
		    fw.flush();
		    fw.close();
		    
		    FileReader fr=new FileReader("C:\\Users\\rajma\\Documents\\Eclipse Java\\BankingSystem\\src\\data\\pass.txt");   //reads the file  
		    BufferedReader br = new BufferedReader(fr);
		    
		    String line;  
		    ArrayList<String[]> al = new ArrayList<String[]>();
		    while( (line = br.readLine()) !=null)  
		    {
		    	String[] ss = line.split(",");
		    	al.add(ss);
		    }
		    
		    for(int i = 0; i < al.size(); i++)
		    {
		    	System.out.println("1)  " + al.get(i));
		    }
		}
		catch(Exception e){
			System.out.println(e);
		}    
		
	}
	
}

//(int accno, int acctype, String name, int age, String gender, String mobile, String password)






