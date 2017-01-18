package app.FrontEnd.Views.modelViews;


import app.Common.models.daModels.eventModels.MeetingDA;
import app.Common.models.daModels.eventModels.TaskDA;

import java.util.Calendar;

public class EventViewerImpl {

    public static String getView(MeetingDA meeting) {
        Calendar meetingDate = meeting.getDateTime();

        return String.format(meetingDate.toString() +
                " " +
                "Meeting:" +
                " " +
                meeting.getName() +
                " Description: " +
                meeting.getDescription());
    }

    public static String getView(TaskDA task) {
        Calendar taskDate = task.getDateTime();

        return String.format(taskDate.getTime().toString() +
                " Task: " +
                task.getName() +
                " Description: " +
                task.getDescription());
    }
}
