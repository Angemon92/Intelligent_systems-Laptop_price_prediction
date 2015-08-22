package logic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Stefan
 */
public class DateManager {

    public static DateManager instance;
    
    public static DateManager getInstance() {
        if (instance == null) {
            instance = new DateManager();
        }
        return instance;
    }

    String getSimpleDateAndTime() {       
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy---HH:mm");
        String date = DATE_FORMAT.format(today);
        return date;
    }
    
    String getSimpleDate() {
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);
        return date;
    }
    
    String getSimpleTime() {
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm");
        String date = DATE_FORMAT.format(today);
        return date;
    }

}
