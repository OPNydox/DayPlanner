package app.BussinessLayer.CalendarElements.Months.Interfaces;

import app.BussinessLayer.CalendarElements.Days.Interfaces.Day;

/**
 * Created by Ico on 3.1.2017 г..
 */
public interface Month {
    Day getDay(int dayNumber);
}
