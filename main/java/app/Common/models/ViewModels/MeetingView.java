package app.Common.models.ViewModels;

/**
 * Created by Ico on 28.12.2016 г..
 */
public class MeetingView extends EventView {
    private String location;

    public MeetingView() {
        super();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
