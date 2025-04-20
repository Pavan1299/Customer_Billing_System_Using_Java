import java.io.Serial;
import java.io.Serializable;

public class Date implements Serializable {

    @Serial
    private static final long serialVersionUID=1L;
    int day,month,year;
    Date(int day,int month,int year){
        this.day=day;
        this.month=month;
        this.year=year;
    }
}