package app.domain.dtos.JSON.importDtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ico on 11.12.2016 г..
 */
public class PhotographerImportJsonDto implements Serializable {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private String phone;

    @Expose
    private List<String> lenses;

    public PhotographerImportJsonDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getLenses() {
        return lenses;
    }

    public void setLenses(List<String> lenses) {
        this.lenses = lenses;
    }
}
