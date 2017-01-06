package app.BussinessLayer.CalendarElements.CustomCalendar;

import app.BussinessLayer.CalendarElements.CustomCalendar.Interfaces.EventCalendar;
import app.BussinessLayer.CalendarElements.Years.Interfaces.Year;
import app.BussinessLayer.CalendarElements.Years.YearImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ico on 3.1.2017 г..
 */
public class EventCalendarImpl implements EventCalendar{
    private Map<Integer ,Year> years;

    public EventCalendarImpl() {
        years = new HashMap<>();
        years.put(2017, new YearImpl(2017));
    }

    private void setYears(Map<Integer,Year> years) {
        this.years = years;
    }


    @Override
    public Year getYear(int number) {
        Year wantedYear = this.years.get(number);

        if (wantedYear != null){
            return wantedYear;
        }

        this.years.put(number, new YearImpl(number));

        wantedYear = this.years.get(number);

        return wantedYear;
    }


}
