package app.BussinessLayer.Factories;

import app.BussinessLayer.Factories.Interfaces.EventFactory;

/**
 * Created by Ico on 29.12.2016 г..
 */
public class FactoryProducer {

    public EventFactory createFactory(String factoryType){
        if(factoryType.equalsIgnoreCase("meeting")){
            return new MeetingFactory();
        } else if (factoryType.equalsIgnoreCase("task")){
            return new TaskFactory();
        } else {
            return null;
        }
    }
}
