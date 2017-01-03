package app.BussinessLayer.Factories;

import app.BussinessLayer.Factories.Interfaces.EventFactory;

/**
 * Created by Ico on 29.12.2016 г..
 */
public class FactoryProducer {

    public EventFactory createFactory(String factoryType) {
        EventFactory finalFactory = null;

        try {
            Class<?> wildFactory = Class.forName("app.BussinessLayer.Factories." + factoryType + "Factory");

            Class<? extends EventFactory > mySubTypeFactory =
                    (wildFactory.asSubclass(EventFactory.class));

            finalFactory = mySubTypeFactory.newInstance();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return finalFactory;
    }
}
