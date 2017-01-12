package app.DataLayer.domain.dtos.JSON.exportDtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by Ico on 11.12.2016 г..
 */

public class PhotographerExportJsonDto implements Serializable{
    @Expose
    private String FirstName;

    @Expose
    private String LastName;

    @Expose
    private String Phone;

    public PhotographerExportJsonDto() {
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
