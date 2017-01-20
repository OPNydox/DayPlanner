package app.backEnd.businessLayer.factories.interfaces;

import app.common.models.ViewModels.EventView;
import app.common.models.daModels.eventModels.EventDA;


/**
 * Created by Ico on 29.12.2016 г..
 */
public interface EventFactory {
    EventDA createEvent(EventView inputEvent);
}
