import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String time_Date = sdf.format(new Date(1596683227411l));
        System.out.println(time_Date);
    }
}
