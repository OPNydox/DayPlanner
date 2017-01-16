package app.BussinessLayer.Factories.Interfaces;

import app.Common.models.ViewModels.EventView;
import app.Common.models.daModels.eventModels.EventDA;


/**
 * Created by Ico on 29.12.2016 г..
 */
public interface EventFactory {
    EventDA createEvent(EventView inputEvent);
}
