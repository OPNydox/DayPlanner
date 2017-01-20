package app.common.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Ico on 14.1.2017 г..
 */
public class DateConverter {

    public static Date calendarToDate(Calendar calendar){
        Date convertedDate = calendar.getTime();
        return convertedDate;
    }

    public static Calendar dateToCalendar(Date date){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static LocalDate dateToLocalDate(Date date){
        LocalDate resultLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return resultLocalDate;
    }

    public static LocalDateTime dateToLocalDateTime(Date date){
        LocalDateTime resultLocalDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return resultLocalDate;
    }

    public static Date LocalDateToDate(LocalDate localDate){
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return date;
    }
}
