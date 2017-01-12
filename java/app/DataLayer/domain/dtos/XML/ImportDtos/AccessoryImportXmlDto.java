package app.DataLayer.domain.dtos.XML.ImportDtos;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by Ico on 11.12.2016 г..
 */

@XmlRootElement(name = "accessories")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessoryImportXmlDto implements Serializable {
    @XmlElement(name = "accessory")
    @XmlAttribute(name = "name")
    private String name;

    public AccessoryImportXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
