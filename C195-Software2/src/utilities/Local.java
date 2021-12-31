package utilities;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public static String getZonedDateTime(String time) {
        try {
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(time, dateTimeFormat);
            ZonedDateTime utcDateTime = localDateTime.atZone(ZoneId.of("UTC"));
            ZoneId zoneID = ZoneId.systemDefault();

            ZonedDateTime zoneDateTime = utcDateTime.withZoneSameInstant(zoneID);
            String zoneTimeString = String.valueOf(zoneDateTime.toLocalTime());
            String zoneDateString = String.valueOf(zoneDateTime.toLocalDate());
            return zoneDateString + " " + zoneTimeString;
        }
        catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(time, dateTimeFormat);
            ZonedDateTime utcDateTime = localDateTime.atZone(ZoneId.of("UTC"));
            ZoneId zoneID = ZoneId.systemDefault();

            ZonedDateTime zoneDateTime = utcDateTime.withZoneSameInstant(zoneID);
            String zoneTimeString = String.valueOf(zoneDateTime.toLocalTime());
            String zoneDateString = String.valueOf(zoneDateTime.toLocalDate());
            return zoneDateString + " " + zoneTimeString;
        }
    }

    public static String getZonedDateTimeInUTC(String time) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(time, dateTimeFormat);
        ZonedDateTime zoneDateTime = localDateTime.atZone(ZoneId.systemDefault());
        ZoneId utcID = ZoneId.of("UTC");

        ZonedDateTime utcDateTime = zoneDateTime.withZoneSameInstant(utcID);
        String utcTimeString = String.valueOf(utcDateTime.toLocalTime());
        String utcDateString = String.valueOf(utcDateTime.toLocalDate());

        return utcDateString + " " + utcTimeString;

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
