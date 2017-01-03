package app.BussinessLayer.CalendarElements.Years;

import app.BussinessLayer.CalendarElements.Months.GregorianMonths.*;
import app.BussinessLayer.CalendarElements.Months.Interfaces.Month;
import app.BussinessLayer.CalendarElements.Years.Interfaces.Year;

import java.util.Date;

/**
 * Created by Ico on 3.1.2017 г..
 */
public class YearImpl  implements Year{
    private int number;

    private Month[] months;

    public YearImpl(int number) {
        Month[] months = new Month[12];

        months[1] = new January();
        months[2] = new February();
        months[3] = new March();
        months[4] = new April();
        months[5] = new May();
        months[6] = new June();
        months[7] = new July();
        months[8] = new August();
        months[9] = new September();
        months[10] = new Octotober();
        months[11] = new November();
        months[12] = new December();

        this.setMonths(months);
        this.setNumber(number);
    }

    @Override
    public Month getMonth(int monthNumber) {
        return this.months[monthNumber];
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    private void setMonths(Month[] months) {
        this.months = months;
    }

    private void setNumber(int number) {
        this.number = number;
    }
}
