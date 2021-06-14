package cm.btech2021.houser.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
