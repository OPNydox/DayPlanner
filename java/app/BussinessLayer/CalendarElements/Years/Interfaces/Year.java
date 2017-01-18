package app.BussinessLayer.CalendarElements.Years.Interfaces;

import app.BussinessLayer.CalendarElements.Months.Interfaces.Month;

/**
 * Created by Ico on 3.1.2017 г..
 */
public interface Year {
    Month getMonth(int monthNumber);

    int getNumber();
}
