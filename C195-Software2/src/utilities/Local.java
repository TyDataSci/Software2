package utilities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Local {
    private static String language = Locale.getDefault().getLanguage();

    public static String getLanguage() {
        return language;
    }

    public static String getTimeZoneId() {
        TimeZone timeZone = TimeZone.getDefault();
        String timeZoneId = timeZone.getDisplayName(true, 0);

        return timeZoneId;
    }

    public static String getNowDateTime() {
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        LocalDateTime ldt = ts.toLocalDateTime();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MMM dd, hh:mm a");
        String nowDateTime = dateTimeFormat.format(ldt);

        return nowDateTime;
    }


    public static String getLocation() {
        String country = Locale.getDefault().getDisplayCountry();
        String location = country + ", " + getTimeZoneId();

        return location;
    }

    public static void setLanguage(String newLanguage) {
        if(newLanguage =="fr" || newLanguage =="en") {
            language = newLanguage;
        }

    }


}
