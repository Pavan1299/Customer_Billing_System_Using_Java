import java.io.*;
import java.util.*;

public class CustomerBilling {
    static final String FILE_NAME="customer_data.ser";
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Customer Billing System : ");
        System.out.println("\n1. Add account to list");
        System.out.println("2. Search customer account");
        System.out.println("3. Exit");

        char choice;
        do{
            System.out.println("Select an option : ");
            choice=sc.nextLine().charAt(0);
        }while(choice<'1'||choice>'3');

        switch(choice){
            case '1'-> addAccount();
            case '2'-> searchAccount();
            case '3'-> {
                System.out.println("Exiting the program...");
                System.exit(0);
            }
        }
    }
    static void addAccount(){
        List<Account> accounts = readAccounts();
        System.out.println("How many customer accounts? ");
        int n=Integer.parseInt(sc.nextLine());
        for(int i=0;i<n;i++){
            int number =accounts.size()+1;
            Account acc=inputAccount(number);
            accounts.add(acc);
        }
        saveAccounts(accounts);
    }

    static Account inputAccount(int number){
        System.out.println("\nCustomer No : "+number);
        System.out.println("Account number : ");
        int account_number=Integer.parseInt(sc.nextLine());
        System.out.println("Name : ");
        String name=sc.nextLine();
        System.out.println("Mobile no: ");
        float mobile_number=Float.parseFloat(sc.nextLine());
        System.out.println("Street : ");
        String street=sc.nextLine();
        System.out.println("City : ");
        String city=sc.nextLine();
        System.out.println("Previous balance : ");
        float oldbalance=Float.parseFloat(sc.nextLine());
        System.out.println("Curent Payment : ");
        float payment=Float.parseFloat(sc.nextLine());
        Date date;
        while(true){
            System.out.println("Payment Date (mm/dd/yyyy)");
            String[] parts=sc.nextLine().split(" ");
            int mm=Integer.parseInt(parts[0]);
            int dd=Integer.parseInt(parts[1]);
            int yyyy=Integer.parseInt(parts[2]);
            if(isValidDate(dd,mm,yyyy)){
                date=new Date(dd,mm,yyyy);
                break;
            }else{
                System.out.println("Invalid date. Please enter a valid calender date.");
            }
        }
        return new Account(number,account_number,name,mobile_number,street,city,oldbalance,payment,date);
    }
    static void searchAccount(){
        List<Account> accounts=readAccounts();
        if(accounts.isEmpty()){
            System.out.println("No Customer data found.");
            return;
        }
        System.out.println("Search by:\n1 - Customer Number\n2 - Customer Name");
        char ch=sc.nextLine().charAt(0);
        switch (ch){
            case '1'->{
                while(true){
                    System.out.println("Enter customer number : ");
                    int num=Integer.parseInt(sc.nextLine());
                    if(num<=0||num>accounts.size()){
                        System.out.println("Invalid customer number.");
                    }else{
                        accounts.get(num-1).display();
                    }
                    System.out.println("Search again? (y/n): ");
                    char again=sc.next().charAt(0);
                    if(again!='y' && again!='Y') break;
                }
            }
            case '2'->{
                System.out.println("Enter name : ");
                String name =sc.nextLine();
                boolean found=false;
                for(Account acc:accounts){
                    if(acc.name.equalsIgnoreCase(name)){
                        acc.display();
                        found=true;
                    }
                }
                if(!found){
                    System.out.println("Customer not found.");
                }
            }
            default -> System.out.println("Invalid Choice.");
        }
    }

    @SuppressWarnings("unchecked")
    static List<Account> readAccounts(){
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(FILE_NAME))){
            return (List<Account>) ois.readObject();
        }
        catch(Exception e){
            return new ArrayList<>();
        }
    }

    static void saveAccounts(List<Account> accounts){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(accounts);
        }
        catch(IOException e){
            System.out.println("Error Saving Accounts.");
        }
    }
    static boolean isValidDate(int d,int m, int y){
        if(y<1||y>9999) return false;
        if(m<1||m>12) return false;
        int[] daysInmonth= {31,(isLeapYear(y)?29:28),31,30,31,30,31,31,30,31,30,31};
        return (d>=1 && d<= daysInmonth[m-1]);
    }

    static boolean isLeapYear(int year){
        return (year%4==0 && (year%100!=0 || year%400==0));
    }
}