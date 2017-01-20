package app.frontEnd.utilities;

import app.backEnd.businessLayer.services.interfaces.EventService;
import app.common.models.ViewModels.EventView;

/**
 * Created by Ico on 15.1.2017 г..
 */
public class Validator {

    public static void validateEvent(EventView eventView, EventService eventService){


        if (eventView.getDescription().isEmpty() || eventView.getDescription() == null){
            throw new IllegalArgumentException("Event must have a description!");
        }

        if (eventView.getMarker().isEmpty() || eventView.getMarker() == null){
            throw new IllegalArgumentException("Event must have a marker!");
        }

        try {
            eventView.getName();
        }catch (NullPointerException ex){
            throw new IllegalArgumentException("Please pick a marker");
        }

        if (eventService.getEventByName(eventView.getName()) != null){
            throw new IllegalArgumentException("Event name must be unique!");
        }
    }

    public static void validateDropDownField(String text){
        if (text.isEmpty() || text == null){
            throw new IllegalArgumentException("Please choose an option!");
        }
    }
}
