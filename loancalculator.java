import java.util.Scanner;

public class loancalculator {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("*****************************************");
        System.out.print("Enter the amount for loan : ");
        int amount = input.nextInt();

        System.out.print("Enter Rate of Interest: ");
        double  RateofInterest = input.nextDouble();

        System.out.print("Enter time rate in years: ");
        double timerate = input.nextDouble();

        double interestamount = (amount*RateofInterest*timerate)/100;
        double totalPayment = (amount + interestamount);
        double monthlypayment =totalPayment /(12*timerate);
        System.out.println("*****************************************");
        System.out.println("Your amount for loan is " + amount);
        System.out.println("Your rate of interest  is " + RateofInterest);
        System.out.println("Time period is " + timerate +" years");
        System.out.println("Your interest amount for the loan is " + interestamount);
        System.out.println("Your monthly payment for the loan is " + monthlypayment);
        System.out.println("Your total payment after the interest rate is "+ totalPayment);
        System.out.println("*****************************************");


    }

}

