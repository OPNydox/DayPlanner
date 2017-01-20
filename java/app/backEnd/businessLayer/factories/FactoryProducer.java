package app.backEnd.businessLayer.factories;

import app.backEnd.businessLayer.factories.interfaces.EventFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Ico on 29.12.2016 г..
 */
@Component
public class FactoryProducer {

    public EventFactory createFactory(String factoryType) {
        EventFactory finalFactory = null;

        try {
            Class<?> wildFactory = Class.forName("app.backEnd.businessLayer.factories." + factoryType + "Factory");

            Class<? extends EventFactory > mySubTypeFactory =
                    (wildFactory.asSubclass(EventFactory.class));

            finalFactory = mySubTypeFactory.newInstance();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return finalFactory;
    }
}
