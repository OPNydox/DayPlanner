package app.Common.models.ViewModels;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ico on 28.12.2016 г..
 */
public abstract class EventView {

    private String name;

    private String marker;

    private Calendar date;

    private String description;

    private String type;


    public EventView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public void setHour(String hour) {
        this.date.add(Calendar.HOUR, Integer.parseInt(hour));
    }

    public void setMinutes(String minutes) {

        this.date.add(Calendar.MINUTE, Integer.parseInt(minutes));
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
