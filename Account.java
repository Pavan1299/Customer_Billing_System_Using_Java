import java.io.Serial;
import java.io.Serializable;

public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID=1L;
    int number;
    int account_number;
    float mobile_number;
    float oldbalance;
    float newbalance;
    float payment;
    Date lastpayment;
    String name;
    String street;
    String city;
    char account_type;
    public Account(int number,int account_number,String name,float mobile_number,String street,String city,float oldbalance,float payment,Date lastpayment){
        this.number=number;
        this.account_number=account_number;
        this.name=name;
        this.mobile_number=mobile_number;
        this.street=street;
        this.city=city;
        this.oldbalance=oldbalance;
        this.payment=payment;
        this.lastpayment=lastpayment;
        this.newbalance=oldbalance-payment;
        if(payment>0){
            this.account_type=(payment<0.1*oldbalance)?'O':'D';
        }
        else{
            this.account_type=(oldbalance>0)?'D':'C';
        }
    }
    public void display(){
        System.out.println("\nCustomer No : "+number);
        System.out.println("Name : "+name);
        System.out.printf("Mobile number : %.0f%n",mobile_number);
        System.out.println("Account no : "+account_number);
        System.out.println("Street : "+street);
        System.out.println("City : "+city);
        System.out.println("Old balance : "+oldbalance);
        System.out.printf("Current Payement : %.2f%n",payment);
        System.out.printf("New Balance : %.2f%n",newbalance);
        System.out.printf("Payment Date : %02d/%02d/%04d%n",lastpayment.month,lastpayment.day,lastpayment.year);
        System.out.println("Account Status : ");
        switch(account_type){
            case 'C'-> System.out.println("Current");
            case 'O'-> System.out.println("Overdue");
            case 'D'-> System.out.println("Delinquent");
            default -> System.out.println("Error");
        }
    }
}